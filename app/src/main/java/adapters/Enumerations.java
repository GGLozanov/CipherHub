package adapters;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Optional;

public abstract class Enumerations {

    public enum Key {
        Title, Description,
        Cipher,
        Reset,
        ButtonOne, ButtonTwo, ButtonThree, ButtonFour,
        Page,
        DemonstrationInputDescription, DemonstrationOutputDescription, KeyDescription,
        Demonstration,
        Visualisation, VisualisationImage
    }

    public enum Screen {
        MainScreen, CaesarScreen,
        VigenereScreen, AtbashScreen,
        PolybiusScreen, A1Z26Screen,
        PlayfairScreen
    }

    public enum Cipher {
        CaesarCipher, VigenereCipher,
        AtbashCipher, PolybiusCipher,
        A1Z26Cipher, PlayfairCipher
    }

    public enum Demonstration {
        CaesarDemonstration, VigenereDemonstration,
        AtbashDemonstration, PolybiusDemonstration,
        A1Z26Demonstration, PlayfairDemonstration
    }

    public enum Page {
        Page1, Page2
    }

    public enum CustomCiphers {
        CustomCaesar, CustomVigenere,
        CustomAtbash, CustomPolybius,
        CustomA1Z26, CustomPlayfair
    }

    public enum Visualisation {
        CaesarVisualisation, VigenereVisualisation,
        AtbashVisualisation, PolybiusVisualisation,
        A1Z26Visualisation, PlayfairVisualisation
    }

}
