package WarlordEmblem;


import WarlordEmblem.cards.Kael.*;
import WarlordEmblem.cards.mantle.*;
import WarlordEmblem.cards.quest.*;
import WarlordEmblem.character.Crowbot;
import WarlordEmblem.character.DeathKnight;
import WarlordEmblem.character.Kael;
import WarlordEmblem.helpers.SecondaryMagicVariable;

import WarlordEmblem.patches.AbstractPlayerEnum;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CharacterSelectScreenPatches;
import WarlordEmblem.relics.*;
import WarlordEmblem.relics.mantle.*;
import WarlordEmblem.relics.quest.*;
import WarlordEmblem.variables.RealmMagicNumber;
import WarlordEmblem.variables.SecondRealmMagicNumber;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.abstracts.CustomRelic;
import basemod.helpers.RelicType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
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
import com.megacrit.cardcrawl.helpers.FontHelper;
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
import java.util.Properties;

import com.evacipated.cardcrawl.mod.stslib.Keyword;
import WarlordEmblem.character.Kael.*;
import WarlordEmblem.cards.DeathKnight.*;
import org.dakiler.slayhelper.relics.mantle.MantleKazakus;


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
    public static String  DK_bgImg = assetPath("img/character/DeathKnight/dk_blood.png");

    public static boolean addonRelic = true;
    public static boolean ringRelic = true;
    public static boolean mantleRelic = true;
    public static Properties WarlordEmblemDefaults = new Properties();

    public static final Color DeathKnight_Color = new Color(0.171F,0.722F,0.722F,1.0F);

 public WarlordEmblem(){
     logger.debug("Constructor started.");
     BaseMod.subscribe(this);
     //CaseMod.subscribe(this);

     BaseMod.addColor(CardColorEnum.DeathKnight_LIME,
             DeathKnight_Color,  DeathKnight_Color,  DeathKnight_Color,  DeathKnight_Color,  DeathKnight_Color,  DeathKnight_Color, DeathKnight_Color,
             assetPath("img/cardui/512/bg_attack_lime.png"),
             assetPath("img/cardui/512/bg_skill_lime.png"),
             assetPath("img/cardui/512/bg_power_lime.png"),
             assetPath("img/cardui/512/card_lime_orb.png"),
             assetPath("img/cardui/1024/bg_attack_lime.png"),
             assetPath("img/cardui/1024/bg_skill_lime.png"),
             assetPath("img/cardui/1024/bg_power_lime.png"),
             assetPath("img/cardui/1024/card_lime_orb.png"),
             assetPath("img/cardui/512/card_lime_small_orb.png")
     );



     loadConfig();
     logger.debug("Constructor finished.");
    }

    public static void initialize() {
        logger.info("========================= 开始初始化 =========================");
        new WarlordEmblem();
        logger.info("========================= 初始化完成 =========================");
    }

    public static void loadConfig() {
        logger.debug("===徽章读取设置======");
        try {
            SpireConfig config = new SpireConfig("WarlordEmblem", "WarlordEmblemSaveData", WarlordEmblemDefaults);
            config.load();
            addonRelic = config.getBool("addonRelic");
            ringRelic = config.getBool("ringRelic");
            mantleRelic = config.getBool("mantleRelic");
        } catch (Exception e) {
            e.printStackTrace();
            clearConfig();
        }
        logger.debug("===徽章读取设置完成======");
    }

    public static void saveConfig() {
        logger.debug("===徽章存储设置======");
        try {
            SpireConfig config = new SpireConfig("WarlordEmblem", "WarlordEmblemSaveData", WarlordEmblemDefaults);
            config.setBool("addonRelic", addonRelic);
            config.setBool("ringRelic", ringRelic);
            config.setBool("mantleRelic", mantleRelic);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("===徽章存储设置完成======");
    }

    public static void clearConfig() {
        saveConfig();
    }


    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(assetPath("/img/badge.png"));
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        ModLabeledToggleButton addonRelicSwitch = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(makeID("RelicFilter")).TEXT[0],400.0f, 720.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,addonRelic, settingsPanel,
                (label) -> {}, (button) -> {addonRelic = button.enabled;saveConfig();});
        ModLabeledToggleButton ringRelicSwitch = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(makeID("RelicFilter")).TEXT[1],400.0f, 660.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,ringRelic, settingsPanel,
                (label) -> {}, (button) -> {ringRelic = button.enabled;saveConfig();});
        ModLabeledToggleButton mantleRelicSwitch = new ModLabeledToggleButton(CardCrawlGame.languagePack.getUIString(makeID("RelicFilter")).TEXT[2],400.0f, 600.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,mantleRelic, settingsPanel,
                (label) -> {}, (button) -> {mantleRelic = button.enabled;saveConfig();});

        settingsPanel.addUIElement(addonRelicSwitch);
        settingsPanel.addUIElement(ringRelicSwitch);
        settingsPanel.addUIElement(mantleRelicSwitch);



    }

    @Override
    public void receiveEditCharacters() {
        logger.info("========================= 开始加载人物 =========================");

        logger.info(DeathKnight.charStrings.NAMES[1]);
        BaseMod.addCharacter(new Kael(Kael.charStrings.NAMES[1],AbstractPlayerEnum.Kael),"images/ui/charSelect/defectButton.png",assetPath("/img/character/Kael/portraitKral.jpg"),AbstractPlayerEnum.Kael);
        //BaseMod.addCharacter(new Crowbot(Crowbot.charStrings.NAMES[1],AbstractPlayerEnum.Crowbot),assetPath("/img/character/Crowbot/crowbotButton.png"),null,AbstractPlayerEnum.Crowbot);
        BaseMod.addCharacter(new DeathKnight(DeathKnight.charStrings.NAMES[1],AbstractPlayerEnum.DeathKnight),assetPath("/img/character/DeathKnight/dkButton.png"),DK_bgImg,AbstractPlayerEnum.DeathKnight);

    }



    @Override
    public void receiveAddAudio() {
        //BaseMod.addAudio(this.makeID("VO_Kael_Intimidate"), assetPath("/audio/sound/Kael/VO/嘲讽2.wav"));
    }


    @Override
    public void receiveEditCards() {
        //BaseMod.addDynamicVariable(new SecondaryMagicVariable());

        BaseMod.addDynamicVariable(new RealmMagicNumber());
        BaseMod.addDynamicVariable(new SecondRealmMagicNumber());

        logger.debug("receiveEditCards started.");
        List<CustomCard> cards = new ArrayList<>();
        cards.add(new Alacrity());
        cards.add(new ChaosMeteor());
        cards.add(new KaelColdSnap());
        cards.add(new DeafeningBlast());
        cards.add(new ElementInvoke());
        cards.add(new EMP());
        cards.add(new ForgeSpirit());
        cards.add(new GhostWalk());
        cards.add(new IceWall());
        cards.add(new SunStrike());
        cards.add(new Tornado());

        cards.add(new Annihilation());
        cards.add(new AntiMagicShield());
        cards.add(new BlackCommand());
        cards.add(new BloodBoiling());
        cards.add(new BloodDiffer());
        cards.add(new BloodMask());
        cards.add(new BloodStrike());
        cards.add(new BodyStrike());
        cards.add(new BoneShield());
        cards.add(new CondemnStrike());
        cards.add(new CurseStrike());
        cards.add(new DeathCoil());
        cards.add(new DeathContract());
        cards.add(new DeathGateway());
        cards.add(new DeathShake());
        cards.add(new DeathStrike());
        cards.add(new DeathWither());
        cards.add(new DiseaseCloud());
        cards.add(new DiseaseStrike());
        cards.add(new DKDefend());
        cards.add(new EvilEager());
        cards.add(new EvilPower());
        cards.add(new EvilSee());
        cards.add(new EvilStrike());
        cards.add(new EvilWorm());
        cards.add(new Execute());
        cards.add(new ForceDeflexion());
        cards.add(new FrozenChain());
        cards.add(new FrozenDeath());
        cards.add(new FrozenHeart());
        cards.add(new FrozenStrike());
        cards.add(new FrozenTouch());
        cards.add(new FrozenWill());
        cards.add(new GhostStrike());
        cards.add(new Hanging());
        cards.add(new HeartStrike());
        cards.add(new Infect());
        cards.add(new LichBody());
        cards.add(new LichkingWraith());
        cards.add(new NoLightShield());
        cards.add(new RuneBlade());
        cards.add(new RuneBurn());
        cards.add(new RuneDefend());
        cards.add(new RuneDiffer());
        cards.add(new RuneExplode());
        cards.add(new RuneFocus());
        cards.add(new RuneHeavyBlow());
        cards.add(new RuneInspire());
        cards.add(new RunePerfusion());
        cards.add(new RuneRecast());
        cards.add(new RuneRecover());
        cards.add(new RuneRegen());
        cards.add(new RuneStorm());
        cards.add(new RuneStrike());
        cards.add(new SwordWave());
        cards.add(new UltimateCold());
        cards.add(new UndeadWill());
        cards.add(new VoidDefend());
        cards.add(new VoidStrike());
        cards.add(new WindStrike());
        cards.add(new WinterHorn());
        cards.add(new Withstand());
        cards.add(new RealmRider());

        cards.add(new MantleCardBaku());
        cards.add(new MantleCardCaireseth());
        cards.add(new MantleCardJean());
        cards.add(new MantleCardKazakus());
        cards.add(new MantleCardLazz());
        cards.add(new MantleCardLiam());
        cards.add(new MantleCardMarkzar());
        cards.add(new MantleCardReynold());
        cards.add(new MantleCardWalana());

        cards.add(new QuestCardDK());
        cards.add(new QuestCardDruid());
        cards.add(new QuestCardHunter());
        cards.add(new QuestCardMage());
        cards.add(new QuestCardPaladin());
        cards.add(new QuestCardPriest());
        cards.add(new QuestCardRogue());
        cards.add(new QuestCardShaman());
        cards.add(new QuestCardWarlock());
        cards.add(new QuestCardWarrior());

        cards.add(new QuestCardDKReward());
        cards.add(new QuestCardDruidReward());
        cards.add(new QuestCardHunterReward());
        cards.add(new QuestCardMageReward());
        cards.add(new QuestCardPaladinReward());
        cards.add(new QuestCardPriestReward());
        cards.add(new QuestCardRogueReward());
        cards.add(new QuestCardShamanReward());
        cards.add(new QuestCardWarlockReward());
        cards.add(new QuestCardWarriorReward());





        for (CustomCard card : cards) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }

        logger.debug("receiveEditCards finished.");
    }


    @Override
    public void receiveEditRelics() {
        logger.debug("receiveEditRelics started.");
        if(addonRelic){
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
            //BaseMod.addRelic(new SoulOfKael(), RelicType.SHARED);

        }

        BaseMod.addRelic(new SoulOfKael(), RelicType.BLUE);
        //BaseMod.addRelicToCustomPool(new SoulOfKael(), CardColorEnum.Kael_LIME);

        BaseMod.addRelicToCustomPool(new RuneSword(), CardColorEnum.DeathKnight_LIME);
        BaseMod.addRelicToCustomPool(new DKHelm(), CardColorEnum.DeathKnight_LIME);
        BaseMod.addRelicToCustomPool(new BloodRealm(), CardColorEnum.DeathKnight_LIME);
        BaseMod.addRelicToCustomPool(new IceRealm(), CardColorEnum.DeathKnight_LIME);
        BaseMod.addRelicToCustomPool(new EvilRealm(), CardColorEnum.DeathKnight_LIME);


        if(mantleRelic){
            BaseMod.addRelic(new MantleBaku(), RelicType.SHARED);
            BaseMod.addRelic(new MantleCaireseth(), RelicType.SHARED);
            BaseMod.addRelic(new MantleJean(), RelicType.SHARED);
            BaseMod.addRelic(new MantleKazakus(), RelicType.SHARED);
            BaseMod.addRelic(new MantleLazz(), RelicType.SHARED);
            BaseMod.addRelic(new MantleLiam(), RelicType.SHARED);
            BaseMod.addRelic(new MantleMarkzar(), RelicType.SHARED);
            BaseMod.addRelic(new MantleReynold(), RelicType.SHARED);
            BaseMod.addRelic(new MantleWalana(), RelicType.SHARED);
        }

        if(ringRelic){
            BaseMod.addRelic(new QuestDK(), RelicType.SHARED);
            BaseMod.addRelic(new QuestDruid(), RelicType.SHARED);
            BaseMod.addRelic(new QuestHunter(), RelicType.SHARED);
            BaseMod.addRelic(new QuestMage(), RelicType.SHARED);
            BaseMod.addRelic(new QuestPaladin(), RelicType.SHARED);
            BaseMod.addRelic(new QuestPriest(), RelicType.SHARED);
            BaseMod.addRelic(new QuestRogue(), RelicType.SHARED);
            BaseMod.addRelic(new QuestShaman(), RelicType.SHARED);
            BaseMod.addRelic(new QuestWarlock(), RelicType.SHARED);
            BaseMod.addRelic(new QuestWarrior(), RelicType.SHARED);
        }


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