package adapters;

import android.content.res.Resources;
import android.os.Bundle;

import com.example.cipherhub.R;

public class Handler {

    // TODO: Limit the. . . overabundance of String keys - the biggest gripe with the current code :(

    private static final String titleKey = Enumerations.Key.Title.name(); // sectionFragment cipher title
    private static final String descriptionKey = Enumerations.Key.Description.name(); // sectionFragment cipher description

    private static final String cipherKey = Enumerations.Key.Cipher.name(); // Which Cipher layout to generate

    private static final String resetKey = Enumerations.Key.Reset.name(); // Reset button

    private static final String buttonOneKey = Enumerations.Key.ButtonOne.name(); // quadrup fragment for different pages
    private static final String buttonTwoKey = Enumerations.Key.ButtonTwo.name();
    private static final String buttonThreeKey = Enumerations.Key.ButtonThree.name();
    private static final String buttonFourKey = Enumerations.Key.ButtonFour.name();

    private static final String pageKey = Enumerations.Key.Page.name();

    private static final String inputDescriptionKey = Enumerations.Key.DemonstrationInputDescription.name(); // which input description to use for which key/no-key cipher
    private static final String outputDescriptionKey = Enumerations.Key.DemonstrationOutputDescription.name(); // which output description to use for which key/no-key cipher

    private static final String cipherkeyDescriptionKey = Enumerations.Key.KeyDescription.name(); // which key description to use for which key cipher

    private static final String cipherDemonstrationKey = Enumerations.Key.Demonstration.name(); // which EditText pairs to use for ciphers when demonstrating

    private static final String visualisationKey = Enumerations.Key.Visualisation.name(); // which Cipher visualisation to choose
    private static final String visualisationImageKey = Enumerations.Key.VisualisationImage.name(); // which image to use for the visualisation cipher

    private static final String MainScreen = Enumerations.Screen.MainScreen.name();
    private static final String CaesarScreen = Enumerations.Screen.CaesarScreen.name();
    private static final String VigenereScreen = Enumerations.Screen.VigenereScreen.name();
    private static final String AtbashScreen = Enumerations.Screen.AtbashScreen.name();
    private static final String PolybiusScreen = Enumerations.Screen.PolybiusScreen.name();
    private static final String A1Z26Screen = Enumerations.Screen.A1Z26Screen.name();
    private static final String PlayfairScreen = Enumerations.Screen.PlayfairScreen.name();

    private static final String Caesar = Enumerations.Cipher.CaesarCipher.name();
    private static final String Vigenere = Enumerations.Cipher.VigenereCipher.name();
    private static final String Atbash = Enumerations.Cipher.AtbashCipher.name();
    private static final String Polybius = Enumerations.Cipher.PolybiusCipher.name();
    private static final String A1Z26 = Enumerations.Cipher.A1Z26Cipher.name();
    private static final String Playfair = Enumerations.Cipher.PlayfairCipher.name();

    private static final String CustomCaesar = Enumerations.CustomCiphers.CustomCaesar.name();
    private static final String CustomVigenere = Enumerations.CustomCiphers.CustomVigenere.name();
    private static final String CustomAtbash = Enumerations.CustomCiphers.CustomAtbash.name();
    private static final String CustomPolybius = Enumerations.CustomCiphers.CustomPolybius.name();
    private static final String CustomA1Z26 = Enumerations.CustomCiphers.CustomA1Z26.name();
    private static final String CustomPlayfair = Enumerations.CustomCiphers.CustomPlayfair.name();

    private static final String CaesarDemonstration = Enumerations.Demonstration.CaesarDemonstration.name();
    private static final String VigenereDemonstration = Enumerations.Demonstration.VigenereDemonstration.name();
    private static final String AtbashDemonstration = Enumerations.Demonstration.AtbashDemonstration.name();
    private static final String PolybiusDemonstration = Enumerations.Demonstration.PolybiusDemonstration.name();
    private static final String A1Z26Demonstration = Enumerations.Demonstration.A1Z26Demonstration.name();
    private static final String PlayfairDemonstration = Enumerations.Demonstration.PlayfairDemonstration.name();

    private static final String Page1 = Enumerations.Page.Page1.name();
    private static final String Page2 = Enumerations.Page.Page2.name();

    // TODO: use enums for switch() and everywhere else -> improve code by x1000 (ok, maybe code isn't the best now...)
    // ENUM-NAME.ENUM-FIELD-NAME.name() method converts enum field to string (!)

    public static String getTitleKey() {return titleKey;}
    public static String getDescriptionKey() {return descriptionKey;}

    public static String getCipherKey() {return cipherKey;}
    public static String getResetKey() {return resetKey;}

    public static String getButtonOneKey() {return buttonOneKey;}
    public static String getButtonTwoKey() {return buttonTwoKey;}
    public static String getButtonThreeKey() {return buttonThreeKey;}
    public static String getButtonFourKey() {return buttonFourKey;}

    public static String getPageKey() {return pageKey;}

    public static String getInputDescriptionKey() {return inputDescriptionKey;}
    public static String getOutputDescriptionKey() {return outputDescriptionKey;}

    public static String getCipherkeyDescriptionKey() {return cipherkeyDescriptionKey;}

    public static String getCipherDemonstrationKey() {return cipherDemonstrationKey;}

    public static String getVisualisationKey() {return visualisationKey;}
    public static String getVisualisationImageKey() {return visualisationImageKey;}

    public static String getMainScreen() {return MainScreen;}
    public static String getCaesarScreen() {return CaesarScreen;}
    public static String getVigenereScreen() {return VigenereScreen;}
    public static String getAtbashScreen() {return AtbashScreen;}
    public static String getPolybiusScreen() {return PolybiusScreen;}
    public static String getA1Z26Screen() {return A1Z26Screen;}
    public static String getPlayfairScreen() {return PlayfairScreen;}

    public static String getCaesar() {return Caesar;}
    public static String getVigenere() {return Vigenere;}
    public static String getAtbash() {return Atbash;}
    public static String getPolybius() {return Polybius;}
    public static String getA1Z26() {return A1Z26;}
    public static String getPlayfair() {return Playfair;}

    public static String getCustomCaesar() {return CustomCaesar;}
    public static String getCustomVigenere() {return CustomVigenere;}
    public static String getCustomAtbash() {return CustomAtbash;}
    public static String getCustomPolybius() {return CustomPolybius;}
    public static String getCustomA1Z26() {return CustomA1Z26;}
    public static String getCustomPlayfair() {return CustomPlayfair;}

    public static String getCaesarDemonstration() {return CaesarDemonstration;}
    public static String getVigenereDemonstration() {return VigenereDemonstration;}
    public static String getAtbashDemonstration() {return AtbashDemonstration;}
    public static String getPolybiusDemonstration() {return PolybiusDemonstration;}
    public static String getA1Z26Demonstration() {return A1Z26Demonstration;}
    public static String getPlayfairDemonstration() {return PlayfairDemonstration;}

    public static String getPage1() {return Page1;}
    public static String getPage2() {return Page2;}

    private Resources appResources;

    public Resources getAppResources() {return appResources;}

    private Bundle bundle = new Bundle();

    public Bundle getBundle() {return bundle;}

    public Handler(Resources appResources) {
        this.appResources = appResources;
    }

    public void handleScreen(Enumerations.Screen screen) {
        // Enum switch for screen

        switch(screen) {
            case MainScreen: // Main Activity
                bundle.putString(titleKey, appResources.getString(R.string.app_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.app_description));
                break;
            case CaesarScreen:
                bundle.putString(titleKey, appResources.getString(R.string.caesar_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.caesar_description));
                break;
            case VigenereScreen:
                bundle.putString(titleKey, appResources.getString(R.string.vigenere_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.vigenere_description));
                break;
            case AtbashScreen:
                bundle.putString(titleKey, appResources.getString(R.string.atbash_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.atbash_description));
                break;
            case PolybiusScreen:
                bundle.putString(titleKey, appResources.getString(R.string.polybius_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.polybius_description));
                break;
            case A1Z26Screen:
                bundle.putString(titleKey, appResources.getString(R.string.a1z26_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.a1z26_description));
                break;
            case PlayfairScreen:
                bundle.putString(titleKey, appResources.getString(R.string.playfair_title));
                bundle.putString(descriptionKey, appResources.getString(R.string.playfair_description));
                break;
        }
    }

    public void handlePage(Enumerations.Page page) {
        // Enum switch for page
        bundle.putString(pageKey, page.name()); // replaced with enums

        switch(page) {
            case Page1: // Page 1 holds the 4 OG ciphers
                bundle.putString(buttonOneKey, appResources.getString(R.string.caesar));
                bundle.putString(buttonTwoKey, appResources.getString(R.string.vigenere));
                bundle.putString(buttonThreeKey, appResources.getString(R.string.atbash));
                bundle.putString(buttonFourKey, appResources.getString(R.string.polybius));
                break;
            case Page2:
                bundle.putString(buttonOneKey, appResources.getString(R.string.a1z26));
                bundle.putString(buttonTwoKey, appResources.getString(R.string.playfair)); // add more ciphers
                bundle.putString(buttonThreeKey, "");
                bundle.putString(buttonFourKey, "");
                break;
        }
    }

    public void handleCipher(Enumerations.Cipher cipher) {
        bundle.putString(cipherKey, cipher.name()); // put name() enum here and access with enum switch with valueOf() later?
    }

    public void handleCustomCipher(Enumerations.CustomCiphers customCiphers) {
        String customCipherName = customCiphers.name();
        bundle.putString(titleKey, customCipherName.substring(0, 6) + ' ' + customCipherName.substring(6)); // split until custom and concat with space

        switch(customCiphers) {
            case CustomCaesar:
                bundle.putString(descriptionKey, appResources.getString(R.string.custom_caesar_description));
                break;
            case CustomVigenere:
                bundle.putString(descriptionKey, appResources.getString(R.string.additional_vigenere_description));
                break;
            case CustomAtbash:
                bundle.putString(descriptionKey, appResources.getString(R.string.custom_atbash_description));
                break;
            case CustomPolybius:
                bundle.putString(descriptionKey, appResources.getString(R.string.custom_polybius_description));
                break;
            case CustomA1Z26:
                bundle.putString(descriptionKey, appResources.getString(R.string.custom_a1z26_description));
                break;
            case CustomPlayfair:
                bundle.putString(descriptionKey, appResources.getString(R.string.custom_playfair_description));
                break;
        }

        bundle.putString(buttonOneKey, appResources.getString(R.string.custom_button));
        bundle.putString(resetKey, appResources.getString(R.string.custom_reset_button));
    }

    public void handleDemonstration(Enumerations.Demonstration demonstration) {
        bundle.putString(cipherDemonstrationKey, demonstration.name());

        switch(demonstration) {
            case CaesarDemonstration:
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.caesar_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.caesar_output_description));
                break;
            case VigenereDemonstration:
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.vigenere_input_description));
                bundle.putString(cipherkeyDescriptionKey, appResources.getString(R.string.vigenere_key_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.vigenere_output_description));
                break;
            case AtbashDemonstration:
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.atbash_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.atbash_output_description));
                break;
            case PolybiusDemonstration:
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.polybius_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.polybius_output_description));
                break;
            case A1Z26Demonstration:
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.a1z26_input_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.a1z26_output_description));
                break;
            case PlayfairDemonstration:
                bundle.putString(inputDescriptionKey, appResources.getString(R.string.playfair_input_description));
                bundle.putString(cipherkeyDescriptionKey, appResources.getString(R.string.playfair_key_description));
                bundle.putString(outputDescriptionKey, appResources.getString(R.string.playfair_otuput_description));
                break;
        }
    }

    public void handleVisualisation(Enumerations.Visualisation visualisation) {
        switch(visualisation) {
            case CaesarVisualisation:
                bundle.putString(visualisationKey, appResources.getString(R.string.caesar_visualisation_description));
                bundle.putString(visualisationImageKey, "CaesarVisualisationImage");
                break;
            case VigenereVisualisation:
                bundle.putString(visualisationKey, appResources.getString(R.string.vigenere_visualisation_description));
                bundle.putString(visualisationImageKey, "VigenereVisualisationImage");
                break;
            case AtbashVisualisation:
                bundle.putString(visualisationKey, appResources.getString(R.string.atbash_visualisation_description));
                bundle.putString(visualisationImageKey, "AtbashVisualisationImage");
                break;
            case PolybiusVisualisation:
                bundle.putString(visualisationKey, appResources.getString(R.string.polybius_visualisation_description));
                bundle.putString(visualisationImageKey, "PolybiusVisualisationImage");
                break;
            case A1Z26Visualisation:
                break;
        }
    }

}
