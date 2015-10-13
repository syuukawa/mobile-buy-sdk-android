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

package com.shopify.buy.ui.cart;

import android.content.Intent;
import android.os.Bundle;

import com.shopify.buy.R;
import com.shopify.buy.ui.collections.CollectionListFragment;
import com.shopify.buy.ui.common.BaseActivity;

public class CartActivity extends BaseActivity {

    protected CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // TODO we should be able refactor most of this into the base class
        if (savedInstanceState == null) {
            cartFragment = getFragment();

            Intent intent = getIntent();
            if (intent != null) {
                cartFragment.setArguments(intent.getExtras());
            }

            getFragmentManager().beginTransaction()
                    .add(R.id.cart_activity, cartFragment)
                    .commit();
        } else {
            cartFragment = (CartFragment) getFragmentManager().findFragmentById(R.id.cart_activity);
        }

        initContentView();
    }

    private CartFragment getFragment() {
        if (cartFragment == null) {
            cartFragment = new CartFragment();
        }
        return cartFragment;
    }

    private void initContentView() {
        setContentView(R.layout.activity_cart);
    }

}
