/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cn.kutils.boxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;

import cn.kutils.boxing.model.BoxingManager;
import cn.kutils.boxing.model.config.BoxingConfig;
import cn.kutils.boxing.model.entity.BaseMedia;
import cn.kutils.boxing.presenter.PickerContract;
import cn.kutils.boxing.presenter.PickerPresenter;

/**
 * A abstract class to connect {@link cn.kutils.boxing.presenter.PickerContract.View} and {@link cn.kutils.boxing.presenter.PickerContract.Presenter}.
 * one job has to be done. override {@link #onCreateBoxingView(ArrayList)} to create a subclass for {@link AbsBoxingViewFragment}.
 *
 * @author ChenSL
 */
public abstract class AbsBoxingActivity extends AppCompatActivity implements Boxing.OnBoxingFinishListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AbsBoxingViewFragment view = onCreateBoxingView(getSelectedMedias(getIntent()));
        BoxingConfig pickerConfig = BoxingManager.getInstance().getBoxingConfig();
        view.setPresenter(new PickerPresenter(view));
        view.setPickerConfig(pickerConfig);
        Boxing.get().setupFragment(view, this);
    }

    private ArrayList<BaseMedia> getSelectedMedias(Intent intent) {
        return intent.getParcelableArrayListExtra(Boxing.EXTRA_SELECTED_MEDIA);
    }

    public BoxingConfig getBoxingConfig() {
        return BoxingManager.getInstance().getBoxingConfig();
    }

    /**
     * create a {@link PickerContract.View} attaching to
     * {@link PickerContract.Presenter},call in {@link #onCreate(Bundle)}
     */
    @NonNull
    public abstract AbsBoxingViewFragment onCreateBoxingView(ArrayList<BaseMedia> medias);

}
