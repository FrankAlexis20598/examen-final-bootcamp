package com.microservicio.atmdeposit.controllers;

import com.microservicio.atmdeposit.exceptions.BlacklistedPersonException;
import com.microservicio.atmdeposit.services.IAtmDepositService;
import com.microservicio.atmdeposit.services.dtos.AtmDepositResponse;
import com.microservicio.atmdeposit.services.dtos.DepositIn;
import com.microservicio.atmdeposit.services.dtos.ValidAccount;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * @author Frank
 */
@RunWith(MockitoJUnitRunner.class)
public class AtmDepositControllerTest {

    @Mock
    private IAtmDepositService atmDepositService;

    @InjectMocks
    private AtmDepositController controller;

    @Test
    public void testDepositAmount() {
        when(atmDepositService.depositAmount(any())).thenReturn(getAtmDepositResponse());
        DepositIn depositIn = new DepositIn("10000000", 100D);
        TestObserver<AtmDepositResponse> testObserver = new TestObserver<>();
        controller.depositAmount(depositIn).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertSubscribed()
                .assertComplete()
                .assertValueCount(1);
    }

    @Test(expected = BlacklistedPersonException.class)
    public void testDepositAmountBlacklistedPersonException() {
        given(atmDepositService.depositAmount(any())).willAnswer(invocation -> {throw new BlacklistedPersonException();});
        DepositIn depositIn = new DepositIn("10000000", 100D);
        BlacklistedPersonException exception = new BlacklistedPersonException();
        controller.depositAmount(depositIn).test()
                .assertSubscribed()
                .assertNotComplete()
                .assertError(new BlacklistedPersonException())
                .assertError(throwable -> throwable.getMessage().equals(exception.getMessage()));
    }

    private AtmDepositResponse createAtmDepositResponse() {
        return new AtmDepositResponse(
                "Core",
                Arrays.asList(
                        new ValidAccount("1111222233334441XXX"),
                        new ValidAccount("1111222233334442XXX"),
                        new ValidAccount("1111222233334443XXX")
                ),
                3100D
        );
    }

    private Single<AtmDepositResponse> getAtmDepositResponse() {
        return Single.just(createAtmDepositResponse());
    }

}