package org.vloiko.currencyrate;

import io.grpc.stub.StreamObserver;
import org.vloiko.currencyrate.rate.CurrencyRate;
import org.vloiko.currencyrate.rate.RateRequest;
import org.vloiko.currencyrate.rate.RateServiceGrpc;

import java.util.stream.IntStream;

public class RateServiceImpl extends RateServiceGrpc.RateServiceImplBase {

    @Override
    public void getRate(RateRequest request, StreamObserver<CurrencyRate> responseObserver) {
        CurrencyRate rate = CurrencyRate.newBuilder()
                .setRate(0.15)
                .build();

        responseObserver.onNext(rate);
        responseObserver.onCompleted();
    }

    @Override
    public void getRateList(RateRequest request, StreamObserver<CurrencyRate> responseObserver) {
        double initRate = 0.15;
        IntStream.rangeClosed(0, 100).forEach(value -> {
            CurrencyRate rate = CurrencyRate.newBuilder()
                    .setRate(initRate + (double)value/100)
                    .build();
            responseObserver.onNext(rate);
        });

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<RateRequest> getAverageRate(StreamObserver<CurrencyRate> responseObserver) {
        return new StreamObserver<>() {
            int count = 0;
            double sum = 0;

            @Override
            public void onNext(RateRequest rateRequest) {
                count++;
                sum += 0.15;
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(CurrencyRate.newBuilder()
                        .setRate(sum/count)
                        .build());
                responseObserver.onCompleted();
            }
        };
    }
}
