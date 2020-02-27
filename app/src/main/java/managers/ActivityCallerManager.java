package managers;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.cipherhub.A1Z26Activity;
import com.example.cipherhub.AtbashActivity;
import com.example.cipherhub.CaesarActivity;
import com.example.cipherhub.PlayfairActivity;
import com.example.cipherhub.PolybiusActivity;
import com.example.cipherhub.VigenereActivity;

public class ActivityCallerManager {

    private Fragment fragment;

    public ActivityCallerManager(Fragment fragment) {
        this.fragment = fragment;
    }

    public void OpenCaesarActivity(){
        Intent CaesarIntent = new Intent(fragment.getActivity(), CaesarActivity.class);

        // first constructor argument takes the CONTEXT of the package (this package, the one we're currently  -> getActiviy() gets current context for fragment).
        // second constructor argument takes the class of the Activity we want to start; we can access the class through the property 'class' of the Activity.
        // Research ContentProviders (abstract data providers) and URI - Uniform Resource Identifier (string of characters that represent a resource).

        fragment.startActivity(CaesarIntent);
    }

    public void OpenVigenereActivity() {
        Intent VigenereIntent = new Intent(fragment.getActivity(), VigenereActivity.class);

        fragment.startActivity(VigenereIntent);
    }

    public void OpenAtbashActivity() {
        Intent AtbashIntent = new Intent(fragment.getActivity(), AtbashActivity.class);

        fragment.startActivity(AtbashIntent);
    }

    public void OpenPolybiusActivity() {
        Intent PolybiusIntent = new Intent(fragment.getActivity(), PolybiusActivity.class);

        fragment.startActivity(PolybiusIntent);
    }

    public void OpenA1Z26Activity() {
        Intent A1Z26Intent = new Intent(fragment.getActivity(), A1Z26Activity.class);

        fragment.startActivity(A1Z26Intent);
    }

    public void OpenPlayfairActivity() {
        Intent playfairIntent = new Intent(fragment.getActivity(), PlayfairActivity.class);

        fragment.startActivity(playfairIntent);
    }
}
