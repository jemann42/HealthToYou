package dd.joshuamann.gthack.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.buttonfinal.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by hussa on 10/15/2017.
 */

public class YoutubeFragment extends Fragment {


    private static String Condition;

    public static void setCondition(String condition) {
        Condition = condition;

        switch (condition) {
            case "Trauma":
                VIDEO_ID = "10jR0zjl19Y";
                break;
            case "Cuts":
                VIDEO_ID = "L77rERL64zc";
                break;
            case "Heart":
                VIDEO_ID = "URN6ZW81v8U";
                break;
            case "Allergies":
                VIDEO_ID = "vkdvfjFep78";
                break;
            case "Poisoning":
                VIDEO_ID = "IEyU-3OpE-A";
                break;
            case "Fainting":
                VIDEO_ID = "LIiuqzvX4vs";
                break;
            default:
                VIDEO_ID = "URN6ZW81v8U";
                break;
        }


    }


    private static final String API_KEY = "AIzaSyDk20UJcPeMLw2HA2KFQh-6RRU5O8SNHuk";

    private static String VIDEO_ID = "vkdvfjFep78";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.you_tube_api, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(API_KEY, new OnInitializedListener() {

            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.loadVideo(VIDEO_ID);
                    player.play();
                }
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });

        return rootView;
    }
}




