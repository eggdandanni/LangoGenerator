package ApiCall;

import com.google.maps.PendingResult;
import com.google.maps.model.PlacesSearchResult;

public class FutureCompletingCallBack implements PendingResult.Callback<PlacesSearchResult[]> {

    @Override
    public void onResult(PlacesSearchResult[] placesSearchResult) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
