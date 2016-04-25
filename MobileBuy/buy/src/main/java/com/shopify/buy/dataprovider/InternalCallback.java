/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Shopify Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.shopify.buy.dataprovider;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by ykulbashian on 16-04-07.
 */
abstract class InternalCallback<T> extends Subscriber<Response<T>> {

    @Override
    public void onCompleted() {
        // Do nothing
    }

    @Override
    public void onNext(Response<T> response) {
        if(response.isSuccessful()){
            if(response.body() != null) {
                success(response.body(), response);
            } else {
                success(null, response);
            }
        } else {
            failure(new RetrofitError(response));
        }
    }

    @Override
    public void onError(Throwable t) {
        if(t instanceof RetrofitError) {
            failure((RetrofitError) t);
        } else {
            failure(RetrofitError.exception(new Exception(t)));
        }
    }

    protected abstract void success(T body, Response response);

    protected abstract void failure(RetrofitError error);
}