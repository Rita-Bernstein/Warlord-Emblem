package WarlordEmblem.patches;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.character.DeathKnight;
import WarlordEmblem.relics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;



public class CharacterSelectScreenPatches
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(WarlordEmblem.makeID("DK_Talent"));
    public static final String[] TEXT = uiStrings.TEXT;
    public static int TalentCount = 1;

    public static Hitbox TalentRight;
    public static Hitbox TalentLeft;
    private static float Talent_RIGHT_W = FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[1], 9999.0F, 0.0F);
    private static float Talent_LEFT_W = FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[1], 9999.0F, 0.0F);

    private static float X_fixed = 30.0f *Settings.scale;

    public static Field charInfoField;


    public static final Logger logger = LogManager.getLogger(WarlordEmblem.class.getSimpleName());
    @SpirePatch(clz = CharacterSelectScreen.class, method = "initialize")
    public static class CharacterSelectScreenPatch_Initialize
    {
        @SpirePostfixPatch
        public static void Initialize(CharacterSelectScreen __instance)
        {
            // Called when you first open the screen, create hitbox for each button
            TalentRight = new Hitbox(Talent_RIGHT_W - 10.0F * Settings.scale + X_fixed, 50.0F * Settings.scale);
            TalentLeft = new Hitbox(Talent_RIGHT_W - 10.0F * Settings.scale + X_fixed, 50.0F * Settings.scale);
            TalentRight.move(Settings.WIDTH / 2.0F - Talent_RIGHT_W / 2.0F - 550.0F * Settings.scale + 16.0f*Settings.scale + X_fixed, 800.0F * Settings.scale);
            TalentLeft.move(Settings.WIDTH / 2.0F - Talent_LEFT_W / 2.0F - 800.0F * Settings.scale + 16.0f*Settings.scale + X_fixed, 800.0F * Settings.scale);


        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
    public static class CharacterSelectScreenPatch_Render
    {
        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance, SpriteBatch sb)
        {
            // Render your buttons/images by passing SpriteBatch
            if (!(TalentCount == 1 ||TalentCount == 2 ||TalentCount == 3 || TalentCount == 4 ))
            {TalentCount = 1;}

            for (CharacterOption o : __instance.options) {
                if (o.name.equals(DeathKnight.charStrings.NAMES[1]) && o.selected) {

                    TalentRight.render(sb);
                    TalentLeft.render(sb);

                    if (TalentRight.hovered || Settings.isControllerMode) {sb.setColor(Color.WHITE);} else {sb.setColor(Color.LIGHT_GRAY);}
                    sb.draw(ImageMaster.CF_RIGHT_ARROW, Settings.WIDTH / 2.0F - Talent_RIGHT_W / 2.0F - 550.0F * Settings.scale + X_fixed, 800.0F * Settings.scale - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale*2.0f, Settings.scale*2.0f, 0.0F, 0, 0, 48, 48, false, false);
                    if (TalentLeft.hovered || Settings.isControllerMode) {sb.setColor(Color.WHITE);} else {sb.setColor(Color.LIGHT_GRAY);}
                    sb.draw(ImageMaster.CF_LEFT_ARROW, Settings.WIDTH / 2.0F - Talent_LEFT_W / 2.0F - 800.0F * Settings.scale + X_fixed, 800.0F * Settings.scale - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale*2.0f, Settings.scale*2.0f, 0.0F, 0, 0, 48, 48, false, false);

                    FontHelper.cardTitleFont.getData().setScale(1.0F);
                    FontHelper.losePowerFont.getData().setScale(0.8F);
                    FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, TEXT[0], Settings.WIDTH / 2.0F - 780.0F * Settings.scale, 850.0F * Settings.scale - 8.0F, Settings.GOLD_COLOR);

                    FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[TalentCount], Settings.WIDTH / 2.0F - 700.0F * Settings.scale, 800.0F * Settings.scale , Settings.GOLD_COLOR);


            }
        }
    }

    }


    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class CharacterSelectScreenPatch_Update
    {
        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance)
        {
            // Update your buttons position, check if the player clicked them, and do something if they did
            for (CharacterOption o : __instance.options) {
                if (o.name.equals(DeathKnight.charStrings.NAMES[1]) && o.selected) {


                    if (InputHelper.justClickedLeft && TalentLeft.hovered) {
                        TalentLeft.clickStarted = true;
                        CardCrawlGame.sound.play("UI_CLICK_1");
                    }
                    if (InputHelper.justClickedLeft && TalentRight.hovered) {
                        TalentRight.clickStarted = true;
                        CardCrawlGame.sound.play("UI_CLICK_1");
                    }

                    if (TalentLeft.justHovered || TalentRight.justHovered) {
                        CardCrawlGame.sound.playV("UI_HOVER", 0.75f);
                    }
//==================================
                    if (!(TalentCount == 1 ||TalentCount == 2 ||TalentCount == 3 ||TalentCount == 4 ))
                    {TalentCount = 1;}


                    if (TalentRight.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                        TalentRight.clicked = false;
                        if (TalentCount < 4) {
                            TalentCount += 1;
                            __instance.bgCharImg = updateBgImg();
                        } else {
                            TalentCount = 1;
                            __instance.bgCharImg = updateBgImg();
                        }
                    }
                    if (TalentLeft.clicked || CInputActionSet.pageLeftViewDeck.isJustPressed()) {
                        TalentLeft.clicked = false;

                        if (TalentCount > 1) {
                            TalentCount -= 1;
                            __instance.bgCharImg = updateBgImg();
                        } else {
                            TalentCount = 4;
                            __instance.bgCharImg = updateBgImg();
                        }
                    }
                    TalentLeft.update();
                    TalentRight.update();
                }}
        }
    }

 @SpirePatch(clz = CharacterOption.class, method = "renderRelics" )
 public  static  class CharacterSelectScreenCharacterOptionPatch_Relic{
     @SpireInsertPatch(rloc = 145,localvars = {"r","i"})
     public  static SpireReturn Insert(CharacterOption obj, @ByRef (type = "relics.AbstractRelic")Object[] _r,int _i){

         if (obj.name.equals(DeathKnight.charStrings.NAMES[1])  && obj.selected && _i == 1) {
            if (TalentCount == 1){
              _r[0] = new BloodRealm();
          }
         if (TalentCount == 2){
             _r[0] = new IceRealm();
         }
         if (TalentCount == 3){
             _r[0] = new EvilRealm();
          }
         if (TalentCount == 4){
                 _r[0] = new DKHelm();
             }
            }
         return SpireReturn.Continue();
     }
 }



    public static Texture updateBgImg(){
        switch (TalentCount ){
            case 1:
                return new Texture(WarlordEmblem.assetPath("/img/character/DeathKnight/dk_blood.png"));
            case 2:
                return new Texture(WarlordEmblem.assetPath("/img/character/DeathKnight/dk_frozen.png"));
            case 3:
                return new Texture(WarlordEmblem.assetPath("/img/character/DeathKnight/dk_evil.png"));
            case 4:
                return new Texture(WarlordEmblem.assetPath("/img/character/DeathKnight/dk_awakened.png"));
            default:
                return  null;
        }
    }
}