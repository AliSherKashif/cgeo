package cgeo.geocaching;

import cgeo.geocaching.files.AbstractFileListActivity;
import cgeo.geocaching.files.IFileSelectionView;
import cgeo.geocaching.files.LocalStorage;
import cgeo.geocaching.settings.Settings;
import cgeo.geocaching.ui.FileSelectionListAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectMapfileActivity extends AbstractFileListActivity<FileSelectionListAdapter> implements IFileSelectionView {

    public SelectMapfileActivity() {
        super("map");
    }

    private String mapFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapFile = Settings.getMapFile();
    }

    @Override
    public void close() {

        Intent intent = new Intent();
        intent.putExtra(Intents.EXTRA_MAP_FILE, mapFile);

        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    protected FileSelectionListAdapter getAdapter(List<File> files) {
        return new FileSelectionListAdapter(this, files);
    }

    @Override
    protected List<File> getBaseFolders() {
        List<File> folders = new ArrayList<File>();
        for (File dir : LocalStorage.getStorages()) {
            folders.add(new File(dir, "mfmaps"));
            folders.add(new File(new File(dir, "Locus"), "mapsVector"));
            folders.add(new File(dir, LocalStorage.cache));
        }
        return folders;
    }

    @Override
    public String getCurrentFile() {
        return mapFile;
    }

    @Override
    public void setCurrentFile(String newFile) {
        mapFile = newFile;
    }

    @Override
    public Context getContext() {
        return this;
    }

}
