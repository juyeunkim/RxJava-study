package com.rxJava.controller;

import com.rxJava.domain.Weather;
import com.rxJava.repository.WeatherRepository;
import com.rxJava.sensor.HumiditySensor;
import com.rxJava.sensor.TemperatureSensor;
import com.rxJava.utils.LogType;
import com.rxJava.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class WeatherController {
    final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;

    private final TemperatureSensor temperatureSensor;
    private final HumiditySensor humiditySensor;
    private final WeatherRepository weatherRepository;
    private SseEmitter emitter;

    private List<Disposable> disposables = new ArrayList<>();

    @Autowired
    public WeatherController(TemperatureSensor temperatureSensor,
                             HumiditySensor humiditySensor,
                             WeatherRepository weatherRepository) {
        this.temperatureSensor = temperatureSensor;
        this.humiditySensor = humiditySensor;
        this.weatherRepository = weatherRepository;
    }

    // Server Sent Event를 이용한 HTTP Streaming 연결
    @CrossOrigin("*")
    @GetMapping("/stream/weather")
    public SseEmitter connectWeatherEvents() {
        emitter = new SseEmitter(SSE_SESSION_TIMEOUT);

        ConnectableObservable<Weather> observable =
                Observable.zip(
                        temperatureSensor.getTemperatureStream(),
                        humiditySensor.getHumidityStream(),
                        (temperature, humidity) -> new Weather(temperature, humidity)
                ).publish(); // 구독자들에게 통지되는 데이터를 브로드 캐스팅한다.

        Disposable disposableSend = sendWeatherData(observable);
        Disposable disposableSave = saveWeatherData(observable);
        disposables.addAll(Arrays.asList(disposableSend, disposableSave));

        observable.connect(); // connect 를 호출하기 전까지는 ConnectableObservable 구독하더라도 데이터가 통지되지 않는다.

        // 구독 해제
       this.dispose(emitter, () ->
                disposables.stream()
                        .filter(disposable -> !disposable.isDisposed())
                        .forEach(Disposable::dispose));
        return emitter;
    }

    private Disposable sendWeatherData(ConnectableObservable<Weather> observable) {
        return observable.subscribe(
                weather -> {
                    emitter.send(weather);
                    Logger.log(LogType.ON_NEXT,
                            weather.getTemperature() + ", " + weather.getHumidity());
                },
                error -> Logger.log(LogType.ON_ERROR, error.getMessage()));
    }

    private Disposable saveWeatherData(ConnectableObservable<Weather> observable) {
        return observable.subscribe(
                weather -> {
                    weatherRepository.save(weather);
                },
                error -> Logger.log(LogType.ON_ERROR, error.getMessage()));
    }

    private void dispose(SseEmitter emitter, Runnable runnable){
        emitter.onCompletion(runnable);
        emitter.onTimeout(runnable);
    }
}