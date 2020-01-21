package com.example.cipherhub;

import org.junit.Test;

import ciphers.A1Z26Cipher;
import ciphers.AtbashCipher;
import ciphers.CaesarCipher;
import ciphers.PolybiusCipher;
import ciphers.VigenereCipher;
import ui.ui_custom.ui_single_key_ciphers.CustomAtbashFragment;
import ui.ui_custom.ui_table_key_ciphers.CustomPolybiusFragment;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void caesar_encode_isCorrect() { // first UnitTest!
        assertEquals(new CaesarCipher(3).CaesarEncode("abc"), "xyz");
    }

    @Test
    public void caesar_encode_isLongCorrect() {
        assertEquals(new CaesarCipher(3).CaesarEncode("Along with the many squeaks and peeps of any unfortunate wildlife that had found its way in the building, only the unorderly static of a cheap and modest television echoed throughout the otherwise desolate, void of activity shack. Nearly all lights in the house were either purposefully shut off to conserve electricity, irrevocably damaged or never existed to begin with. Unwashed dishes, glasses and silverware were all piled up in the kitchen sink in a pervasive and grotesque manner that would make most people borderline nauseous. Such a situation was all-too familiar to Stanley Filbrick Pines, self-proclaimed 'Man of Mystery' - from his own meagre household attire, to the usual Gravity Falls public access TV evening program schedule - there was no mistaking it: this disparaging sight was the reality of his life, coalesced in the confines of his living room. Admittedly, he didn’t find that epiphany all that distressing. As a most glaring example, the fact that he had gotten used to the lack of overall household maintenance in the sections of the house not dedicated solely to enamouring tourists - or those he inhabited - was telling of Stanley’s characteristic frugality and penury when it came to life outside the costume. He also knew he couldn’t ask his employees to do all the housework either way; even he realised it would be too much. And he grew to appreciate some aspects of his decadent lifestyle - most notably the ability to dress or eat however and whenever he wanted to. So that didn’t bother him that much. But the monotony and facetiousness which truly permeated his daily life did: wake up, eat, scam, eat, scam, work in the basement until he drops asleep and do it all again the next day; it wasn't a routine he grew to enjoy, and he always felt powerless to alter it. Stan still somehow allowed himself to care either way."),
                "Xilkd tfqe qeb jxkv pnrbxhp xka mbbmp lc xkv rkcloqrkxqb tfiaifcb qexq exa clrka fqp txv fk qeb yrfiafkd, lkiv qeb rkloaboiv pqxqfz lc x zebxm xka jlabpq qbibsfpflk bzelba qeolrdelrq qeb lqebotfpb abplixqb, slfa lc xzqfsfqv pexzh. Kbxoiv xii ifdeqp fk qeb elrpb tbob bfqebo mromlpbcriiv perq lcc ql zlkpbosb bibzqofzfqv, foobslzxyiv axjxdba lo kbsbo bufpqba ql ybdfk tfqe. Rktxpeba afpebp, dixppbp xka pfisbotxob tbob xii mfiba rm fk qeb hfqzebk pfkh fk x mbosxpfsb xka dolqbpnrb jxkkbo qexq tlria jxhb jlpq mblmib yloaboifkb kxrpblrp. Prze x pfqrxqflk txp xii-qll cxjfifxo ql Pqxkibv Cfiyofzh Mfkbp, pbic-molzixfjba 'Jxk lc Jvpqbov' - colj efp ltk jbxdob elrpbelia xqqfob, ql qeb rprxi Doxsfqv Cxiip mryifz xzzbpp QS bsbkfkd moldoxj pzebarib - qebob txp kl jfpqxhfkd fq: qefp afpmxoxdfkd pfdeq txp qeb obxifqv lc efp ifcb, zlxibpzba fk qeb zlkcfkbp lc efp ifsfkd ollj. Xajfqqbaiv, eb afak’q cfka qexq bmfmexkv xii qexq afpqobppfkd. Xp x jlpq dixofkd buxjmib, qeb cxzq qexq eb exa dlqqbk rpba ql qeb ixzh lc lsboxii elrpbelia jxfkqbkxkzb fk qeb pbzqflkp lc qeb elrpb klq abafzxqba plibiv ql bkxjlrofkd qlrofpqp - lo qelpb eb fkexyfqba - txp qbiifkd lc Pqxkibv’p zexoxzqbofpqfz cordxifqv xka mbkrov tebk fq zxjb ql ifcb lrqpfab qeb zlpqrjb. Eb xipl hkbt eb zlriak’q xph efp bjmilvbbp ql al xii qeb elrpbtloh bfqebo txv; bsbk eb obxifpba fq tlria yb qll jrze. Xka eb dobt ql xmmobzfxqb pljb xpmbzqp lc efp abzxabkq ifcbpqvib - jlpq klqxyiv qeb xyfifqv ql aobpp lo bxq eltbsbo xka tebkbsbo eb txkqba ql. Pl qexq afak’q ylqebo efj qexq jrze. Yrq qeb jlklqlkv xka cxzbqflrpkbpp tefze qoriv mbojbxqba efp axfiv ifcb afa: txhb rm, bxq, pzxj, bxq, pzxj, tloh fk qeb yxpbjbkq rkqfi eb aolmp xpibbm xka al fq xii xdxfk qeb kbuq axv; fq txpk'q x olrqfkb eb dobt ql bkglv, xka eb xitxvp cbiq mltboibpp ql xiqbo fq. Pqxk pqfii pljbelt xiiltba efjpbic ql zxob bfqebo txv.");
    }

    @Test
    public void caesar_decode_isLongCorrect() {
        assertEquals(new CaesarCipher(3).CaesarDecode("Along with the many squeaks and peeps of any unfortunate wildlife that had found its way in the building, only the unorderly static of a cheap and modest television echoed throughout the otherwise desolate, void of activity shack. Nearly all lights in the house were either purposefully shut off to conserve electricity, irrevocably damaged or never existed to begin with. Unwashed dishes, glasses and silverware were all piled up in the kitchen sink in a pervasive and grotesque manner that would make most people borderline nauseous. Such a situation was all-too familiar to Stanley Filbrick Pines, self-proclaimed 'Man of Mystery' - from his own meagre household attire, to the usual Gravity Falls public access TV evening program schedule - there was no mistaking it: this disparaging sight was the reality of his life, coalesced in the confines of his living room. Admittedly, he didn’t find that epiphany all that distressing. As a most glaring example, the fact that he had gotten used to the lack of overall household maintenance in the sections of the house not dedicated solely to enamouring tourists - or those he inhabited - was telling of Stanley’s characteristic frugality and penury when it came to life outside the costume. He also knew he couldn’t ask his employees to do all the housework either way; even he realised it would be too much. And he grew to appreciate some aspects of his decadent lifestyle - most notably the ability to dress or eat however and whenever he wanted to. So that didn’t bother him that much. But the monotony and facetiousness which truly permeated his daily life did: wake up, eat, scam, eat, scam, work in the basement until he drops asleep and do it all again the next day; it wasn't a routine he grew to enjoy, and he always felt powerless to alter it. Stan still somehow allowed himself to care either way."),
                "Dorqj zlwk wkh pdqb vtxhdnv dqg shhsv ri dqb xqiruwxqdwh zlogolih wkdw kdg irxqg lwv zdb lq wkh exloglqj, rqob wkh xqrughuob vwdwlf ri d fkhds dqg prghvw whohylvlrq hfkrhg wkurxjkrxw wkh rwkhuzlvh ghvrodwh, yrlg ri dfwlylwb vkdfn. Qhduob doo oljkwv lq wkh krxvh zhuh hlwkhu sxusrvhixoob vkxw rii wr frqvhuyh hohfwulflwb, luuhyrfdeob gdpdjhg ru qhyhu halvwhg wr ehjlq zlwk. Xqzdvkhg glvkhv, jodvvhv dqg vloyhuzduh zhuh doo slohg xs lq wkh nlwfkhq vlqn lq d shuydvlyh dqg jurwhvtxh pdqqhu wkdw zrxog pdnh prvw shrsoh erughuolqh qdxvhrxv. Vxfk d vlwxdwlrq zdv doo-wrr idploldu wr Vwdqohb Iloeulfn Slqhv, vhoi-surfodlphg 'Pdq ri Pbvwhub' - iurp klv rzq phdjuh krxvhkrog dwwluh, wr wkh xvxdo Judylwb Idoov sxeolf dffhvv WY hyhqlqj surjudp vfkhgxoh - wkhuh zdv qr plvwdnlqj lw: wklv glvsdudjlqj vljkw zdv wkh uhdolwb ri klv olih, frdohvfhg lq wkh frqilqhv ri klv olylqj urrp. Dgplwwhgob, kh glgq’w ilqg wkdw hslskdqb doo wkdw glvwuhvvlqj. Dv d prvw jodulqj hadpsoh, wkh idfw wkdw kh kdg jrwwhq xvhg wr wkh odfn ri ryhudoo krxvhkrog pdlqwhqdqfh lq wkh vhfwlrqv ri wkh krxvh qrw ghglfdwhg vrohob wr hqdprxulqj wrxulvwv - ru wkrvh kh lqkdelwhg - zdv whoolqj ri Vwdqohb’v fkdudfwhulvwlf iuxjdolwb dqg shqxub zkhq lw fdph wr olih rxwvlgh wkh frvwxph. Kh dovr nqhz kh frxogq’w dvn klv hpsorbhhv wr gr doo wkh krxvhzrun hlwkhu zdb; hyhq kh uhdolvhg lw zrxog eh wrr pxfk. Dqg kh juhz wr dssuhfldwh vrph dvshfwv ri klv ghfdghqw olihvwboh - prvw qrwdeob wkh delolwb wr guhvv ru hdw krzhyhu dqg zkhqhyhu kh zdqwhg wr. Vr wkdw glgq’w erwkhu klp wkdw pxfk. Exw wkh prqrwrqb dqg idfhwlrxvqhvv zklfk wuxob shuphdwhg klv gdlob olih glg: zdnh xs, hdw, vfdp, hdw, vfdp, zrun lq wkh edvhphqw xqwlo kh gursv dvohhs dqg gr lw doo djdlq wkh qhaw gdb; lw zdvq'w d urxwlqh kh juhz wr hqmrb, dqg kh dozdbv ihow srzhuohvv wr dowhu lw. Vwdq vwloo vrphkrz doorzhg klpvhoi wr fduh hlwkhu zdb." );
    }

    @Test
    public void caesar_encode_isSpecialCorrect() {
        assertEquals(new CaesarCipher(3).CaesarEncode("???....,,,----'''''"), "???....,,,----'''''");
    }

    @Test
    public void caesar_encode_isInvalidCorrect() {
        assertEquals(new CaesarCipher(3).CaesarEncode("[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^"), "");
    }

    @Test
    public void vigenere_encode_isCorrect() {
        assertEquals(new VigenereCipher().VigenereEncode("HelloHelloH", "Hi, Rachel!"), "Om, clqoiw!");
    }

    @Test
    public void vigenere_encode_isLongCorrect() {
        assertEquals(new VigenereCipher().VigenereEncode("HelpHelpHelpHelpHelpHelpHelpHelpHelpHelpHelp", "LongLongLongLongLongLongLongLongLongLongLong"), "SsyvSsyvSsyvSsyvSsyvSsyvSsyvSsyvSsyvSsyvSsyv");
    }

    @Test
    public void viginere_encode_isSpecialCorrect() {
        assertEquals(new VigenereCipher().VigenereEncode("HelpHelpHelpHelpHelpHelpHelpHelpHelpHelpHelp", "?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-."), "?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.?,-.");
    }

    @Test
    public void vigenere_encode_isInvalidCorrect() {
        assertEquals(new VigenereCipher().VigenereEncode("HelpHelpHelpHelpHelpHelpHelpHelpHelpHelpHelp", "[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^[]_^"), "");
    }

    @Test
    public void atbash_encode_isCorrect() {
        AtbashCipher atbashCipher = new AtbashCipher(CustomAtbashFragment.getAtbashKey());
        assertEquals(atbashCipher.AtbashEncode("Hello, Mark!"), "Svool, Nzip!");
    }

    @Test
    public void atbash_encode_isSpecialCorrect() {
        AtbashCipher atbashCipher = new AtbashCipher(CustomAtbashFragment.getAtbashKey());
        assertEquals(atbashCipher.AtbashEncode("???....,,,----'''''"), "???....,,,----'''''");
    }

    @Test
    public void atbash_encode_isInvalidCorrect() {
        AtbashCipher atbashCipher = new AtbashCipher("abcdefghijklmnopqrstuvwxyz");
        assertEquals(atbashCipher.AtbashEncode("[]_^"), "");
    }

    @Test
    public void atbash_encode_isLongCorrect() {
        AtbashCipher atbashCipher = new AtbashCipher("abcdefghijklmnopqrstuvwxyz");
        assertEquals(atbashCipher.AtbashEncode("Along with the many squeaks and peeps of any unfortunate wildlife that had found its way in the building, only the unorderly static of a cheap and modest television echoed throughout the otherwise desolate, void of activity shack. Nearly all lights in the house were either purposefully shut off to conserve electricity, irrevocably damaged or never existed to begin with. Unwashed dishes, glasses and silverware were all piled up in the kitchen sink in a pervasive and grotesque manner that would make most people borderline nauseous. Such a situation was all-too familiar to Stanley Filbrick Pines, self-proclaimed 'Man of Mystery' - from his own meagre household attire, to the usual Gravity Falls public access TV evening program schedule - there was no mistaking it: this disparaging sight was the reality of his life, coalesced in the confines of his living room. Admittedly, he didn’t find that epiphany all that distressing. As a most glaring example, the fact that he had gotten used to the lack of overall household maintenance in the sections of the house not dedicated solely to enamouring tourists - or those he inhabited - was telling of Stanley’s characteristic frugality and penury when it came to life outside the costume. He also knew he couldn’t ask his employees to do all the housework either way; even he realised it would be too much. And he grew to appreciate some aspects of his decadent lifestyle - most notably the ability to dress or eat however and whenever he wanted to. So that didn’t bother him that much. But the monotony and facetiousness which truly permeated his daily life did: wake up, eat, scam, eat, scam, work in the basement until he drops asleep and do it all again the next day; it wasn't a routine he grew to enjoy, and he always felt powerless to alter it. Stan still somehow allowed himself to care either way."), "Zolmt drgs gsv nzmb hjfvzph zmw kvvkh lu zmb fmuligfmzgv droworuv gszg szw ulfmw rgh dzb rm gsv yfrowrmt, lmob gsv fmliwviob hgzgrx lu z xsvzk zmw nlwvhg gvoverhrlm vxslvw gsilftslfg gsv lgsvidrhv wvhlozgv, elrw lu zxgrergb hszxp. Mvziob zoo ortsgh rm gsv slfhv dviv vrgsvi kfiklhvufoob hsfg luu gl xlmhviev vovxgirxrgb, riivelxzyob wznztvw li mvevi vcrhgvw gl yvtrm drgs. Fmdzhsvw wrhsvh, tozhhvh zmw hroevidziv dviv zoo krovw fk rm gsv prgxsvm hrmp rm z kviezhrev zmw tilgvhjfv nzmmvi gszg dlfow nzpv nlhg kvlkov yliwviormv mzfhvlfh. Hfxs z hrgfzgrlm dzh zoo-gll uznrorzi gl Hgzmovb Uroyirxp Krmvh, hvou-kilxozrnvw 'Nzm lu Nbhgvib' - uiln srh ldm nvztiv slfhvslow zggriv, gl gsv fhfzo Tizergb Uzooh kfyorx zxxvhh GE vevmrmt kiltizn hxsvwfov - gsviv dzh ml nrhgzprmt rg: gsrh wrhkziztrmt hrtsg dzh gsv ivzorgb lu srh oruv, xlzovhxvw rm gsv xlmurmvh lu srh orermt illn. Zwnrggvwob, sv wrwm'g urmw gszg vkrkszmb zoo gszg wrhgivhhrmt. Zh z nlhg tozirmt vcznkov, gsv uzxg gszg sv szw tlggvm fhvw gl gsv ozxp lu levizoo slfhvslow nzrmgvmzmxv rm gsv hvxgrlmh lu gsv slfhv mlg wvwrxzgvw hlovob gl vmznlfirmt glfirhgh - li gslhv sv rmszyrgvw - dzh gvoormt lu Hgzmovb’h xszizxgvirhgrx uiftzorgb zmw kvmfib dsvm rg xznv gl oruv lfghrwv gsv xlhgfnv. Sv zohl pmvd sv xlfowm’g zhp srh vnkolbvvh gl wl zoo gsv slfhvdlip vrgsvi dzb; vevm sv ivzorhvw rg dlfow yv gll nfxs. Zmw sv tivd gl zkkivxrzgv hlnv zhkvxgh lu srh wvxzwvmg oruvhgbov - nlhg mlgzyob gsv zyrorgb gl wivhh li vzg sldvevi zmw dsvmvevi sv dzmgvw gl. Hl gszg wrwm’g ylgsvi srn gszg nfxs. Yfg gsv nlmlglmb zmw uzxvgrlfhmvhh dsrxs gifob kvinvzgvw srh wzrob oruv wrw: dzpv fk, vzg, hxzn, vzg, hxzn, dlip rm gsv yzhvnvmg fmgro sv wilkh zhovvk zmw wl rg zoo ztzrm gsv mvcg wzb; rg dzhm'g z ilfgrmv sv tivd gl vmqlb, zmw sv zodzbh uvog kldviovhh gl zogvi rg. Hgzm hgroo hlnvsld zooldvw srnhvou gl xziv vrgsvi dzb.");
    }

    @Test
    public void polybius_encode_isCorrect() {
        assertEquals(new PolybiusCipher(CustomPolybiusFragment.getPolybiusKey()).PolybiusEncode("Gravity"), "21361144234251");
    }

    @Test
    public void polybius_encode_isLongCorrect() {
        // assertEquals(new PolybiusCipher(CustomPolybiusFragment.getPolybiusKey()).PolybiusEncode(""));
    }

    @Test
    public void A1Z26_encode_isCorrect() {
        assertEquals(new A1Z26Cipher("abcdefghijklmnopqrstuvwxyz").A1Z26Encode("Hello"), "8-5-12-12-15");
    }

    @Test
    public void A1Z26_decode_isCorrect() {
        assertEquals(new A1Z26Cipher("abcdefghijklmnopqrstuvwxyz").A1Z26Decode("8-5-12-12-15"), "hello");
    }
}

