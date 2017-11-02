package com.example.thuyhien.simplelogin.async;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/16/17.
 */

public class DownloadFeedTask extends AsyncTask<Section, Void, List<Section>> {

    private LoadDataListener<List<Section>> listener;
    private LoadDataInteractor loadDataInteractor;

    public DownloadFeedTask(LoadDataInteractor loadDataInteractor, LoadDataListener<List<Section>> listener) {
        this.loadDataInteractor = loadDataInteractor;
        this.listener = listener;
    }

    @Override
    protected List<Section> doInBackground(Section... sections) {
        if (sections != null) {
//            return loadDataInteractor.getTotalFeedList(sections);
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Section> sectionList) {
        listener.onLoadDataSuccess(sectionList);
    }

//    private void createRadioDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle(R.string.title_dialog_language)
//                .setSingleChoiceItems(languageNameList.toArray(new String[languageNameList.size()]),
//                        currentLangIndex, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                            }
//                        })
//                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        int selectedPost = ((AlertDialog) dialogInterface).getListView()
//                                .getCheckedItemPosition();
//                        if (selectedPost != currentLangIndex) {
//                            settingsPresenter.saveChosenLanguage();
//                            displayChosenLanguage();
//                        }
//                    }
//                });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}
