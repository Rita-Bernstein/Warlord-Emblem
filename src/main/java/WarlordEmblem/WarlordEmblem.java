package WarlordEmblem;

//import WarlordEmblem.cards.*;
import WarlordEmblem.cards.*;
import WarlordEmblem.character.Crowbot;
import WarlordEmblem.character.DeathKnight;
import WarlordEmblem.character.Kael;
import WarlordEmblem.helpers.SecondaryMagicVariable;

import WarlordEmblem.patches.AbstractPlayerEnum;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CharacterSelectScreenPatches;
import WarlordEmblem.relics.*;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import WarlordEmblem.character.Kael.*;
//import WarlordEmblem.cards.*;


@SpireInitializer
public class WarlordEmblem implements
        PostInitializeSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        AddAudioSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber{
    public static final Logger logger = LogManager.getLogger(WarlordEmblem.class.getSimpleName());


    public static String MOD_ID = "WarlordEmblem";
    public static String makeID(String id) {
        return MOD_ID + ":" + id;
    }
    public static String assetPath(String path) {
        return MOD_ID + "/" + path;
    }

    public static final String MODNAME = "Warlord Emblem";
    public static final String AUTHOR = "Rita";
    public static final String DESCRIPTION = "Porting Warlord Emblem to latest version.";
    public static String  DK_bgImg = assetPath("/img/character/DeathKnight/dk_blood.png");

 public WarlordEmblem(){
     logger.debug("Constructor started.");
     BaseMod.subscribe(this);
     //CaseMod.subscribe(this);

     logger.debug("Constructor finished.");
    }

    public static void initialize() {
        logger.info("========================= 开始初始化 =========================");
        new WarlordEmblem();
        logger.info("========================= 初始化完成 =========================");
    }


    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(assetPath("/img/badge.png"));
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("========================= 开始加载人物 =========================");

        logger.info(DeathKnight.charStrings.NAMES[1]);
        BaseMod.addCharacter(new Kael(Kael.charStrings.NAMES[1],AbstractPlayerEnum.Kael),"images/ui/charSelect/defectButton.png",assetPath("/img/character/Kael/portraitKral.jpg"),AbstractPlayerEnum.Kael);
        BaseMod.addCharacter(new Crowbot(Crowbot.charStrings.NAMES[1],AbstractPlayerEnum.Crowbot),assetPath("/img/character/Crowbot/crowbotButton.png"),null,AbstractPlayerEnum.Crowbot);
        BaseMod.addCharacter(new DeathKnight(DeathKnight.charStrings.NAMES[1],AbstractPlayerEnum.DeathKnight),assetPath("/img/character/DeathKnight/dkButton.png"),DK_bgImg,AbstractPlayerEnum.DeathKnight);

    }



    @Override
    public void receiveAddAudio() {
        //BaseMod.addAudio(this.makeID("VO_Kael_Intimidate"), assetPath("/audio/sound/Kael/VO/嘲讽2.wav"));
    }


    @Override
    public void receiveEditCards() {
        //BaseMod.addDynamicVariable(new SecondaryMagicVariable());


        logger.debug("receiveEditCards started.");
        List<CustomCard> cards = new ArrayList<>();
        cards.add(new Alacrity());
        cards.add(new ChaosMeteor());
        cards.add(new KralColdSnap());
        cards.add(new DeafeningBlast());
        cards.add(new ElementInvoke());
        cards.add(new EMP());
        cards.add(new ForgeSpirit());
        cards.add(new GhostWalk());
        cards.add(new IceWall());
        cards.add(new SunStrike());
        cards.add(new Tornado());



        for (CustomCard card : cards) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }

        logger.debug("receiveEditCards finished.");
    }


    @Override
    public void receiveEditRelics() {
        logger.debug("receiveEditRelics started.");

        BaseMod.addRelic(new BadgeBless(), RelicType.SHARED);
        BaseMod.addRelic(new BadgeWrath(), RelicType.SHARED);
        BaseMod.addRelic(new EvilBlood(), RelicType.SHARED);
        BaseMod.addRelic(new HatTrick(), RelicType.SHARED);
        BaseMod.addRelic(new HeavyShield(), RelicType.SHARED);
        BaseMod.addRelic(new OldTorch(), RelicType.SHARED);
        //BaseMod.addRelic(new Pill(), RelicType.SHARED);
        //BaseMod.addRelic(new BurningIceCream(), RelicType.SHARED);
        BaseMod.addRelic(new SaveLoadDisk(), RelicType.SHARED);
        BaseMod.addRelic(new SmileMask(), RelicType.SHARED);
        BaseMod.addRelic(new SoftArmor(), RelicType.SHARED);
        BaseMod.addRelic(new VoidPower(), RelicType.SHARED);
        BaseMod.addRelic(new BrokenMantle(), RelicType.SHARED);
        BaseMod.addRelic(new Scar(), RelicType.SHARED);
        BaseMod.addRelic(new BrokenWatch(), RelicType.SHARED);
        BaseMod.addRelic(new WuziBook(), RelicType.SHARED);
        BaseMod.addRelic(new BadgeProtect(), RelicType.SHARED);
        BaseMod.addRelic(new KitchenKnife(), RelicType.SHARED);
        BaseMod.addRelic(new BadgeMouse(), RelicType.SHARED);
        BaseMod.addRelic(new GoldMime(), RelicType.SHARED);
        BaseMod.addRelic(new BreastProtector(), RelicType.SHARED);
        BaseMod.addRelic(new SoulOfKael(), RelicType.SHARED);

        BaseMod.addRelicToCustomPool(new SoulOfKael(), CardColorEnum.Kael_LIME);

        logger.debug("receiveEditRelics finished.");
    }

    private Settings.GameLanguage languageSupport()
    {
        switch (Settings.language) {
            case ZHS:
                return Settings.language;
            //case JPN:
            //    return Settings.language;
            default:
                return Settings.GameLanguage.ENG;
        }
    }
    public void receiveEditStrings()
    {
        Settings.GameLanguage language = languageSupport();

        // Load english first to avoid crashing if translation doesn't exist for something
        loadLocStrings(Settings.GameLanguage.ENG);
        if(!language.equals(Settings.GameLanguage.ENG)) {
            loadLocStrings(language);
        }

    }

    private void loadLocStrings(Settings.GameLanguage language)
    {
        String path = "localization/" + language.toString().toLowerCase() + "/";

        BaseMod.loadCustomStringsFile(EventStrings.class, assetPath(path + "EventStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, assetPath(path + "UIStrings.json"));
        //BaseMod.loadCustomStringsFile(PotionStrings.class, assetPath(path + "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(CardStrings.class, assetPath(path + "CardStrings.json"));
        //BaseMod.loadCustomStringsFile(MonsterStrings.class, assetPath(path + "monsters.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, assetPath(path + "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, assetPath(path + "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, assetPath(path + "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class, assetPath(path + "OrbStrings.json"));


    }


    private void loadLocKeywords(Settings.GameLanguage language)
    {
        String path = "localization/" + language.toString().toLowerCase() + "/";
        Gson gson = new Gson();
        String json = Gdx.files.internal(assetPath(path + "KeywordStrings.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        logger.info("========================= 开始加载关键字 =========================");
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("warlord_emblem", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditKeywords()
    {

        Settings.GameLanguage language = languageSupport();

        // Load english first to avoid crashing if translation doesn't exist for something
        loadLocKeywords(Settings.GameLanguage.ENG);
        if(!language.equals(Settings.GameLanguage.ENG)) {
            loadLocKeywords(language);
        }


    }
}