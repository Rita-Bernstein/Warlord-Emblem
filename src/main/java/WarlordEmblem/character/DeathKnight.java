package WarlordEmblem.character;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.DeathKnight.*;
import WarlordEmblem.modules.EnergyOrbCustomBlue;
import WarlordEmblem.patches.AbstractPlayerEnum;
import WarlordEmblem.patches.CharacterSelectScreenPatches;
import WarlordEmblem.relics.BloodRealm;
import WarlordEmblem.relics.EvilRealm;
import WarlordEmblem.relics.IceRealm;
import WarlordEmblem.relics.RuneSword;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.BurningBlood;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;

import java.util.ArrayList;

import static WarlordEmblem.patches.CharacterSelectScreenPatches.TalentCount;

public class DeathKnight extends CustomPlayer {
	public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString(WarlordEmblem.makeID("DeathKnight"));

	public static final int ENERGY_PER_TURN = 3;
	public static final int START_HP = 80;
	public static final int START_GOLD = 50;


	public static final String[] orbTextures = {
			"images/ui/topPanel/blue/1.png",//4
			"images/ui/topPanel/blue/2.png",//2
			"images/ui/topPanel/blue/3.png",//3
			"images/ui/topPanel/blue/4.png",//5
			"images/ui/topPanel/blue/5.png",//1
			"images/ui/topPanel/blue/border.png",
			"images/ui/topPanel/blue/1d.png",
			"images/ui/topPanel/blue/2d.png",
			"images/ui/topPanel/blue/3d.png",
			"images/ui/topPanel/blue/4d.png",
			"images/ui/topPanel/blue/5d.png"
	};

	public DeathKnight(String name, PlayerClass setClass) {
		super(name, setClass, new EnergyOrbCustomBlue(orbTextures, "images/ui/topPanel/energyBlueVFX.png"),  (String) null, null);
		this.drawX += 5.0F * Settings.scale;
		this.drawY += 7.0F * Settings.scale;

		this.dialogX = this.drawX + 0.0F * Settings.scale;
		this.dialogY = this.drawY + 170.0F * Settings.scale;

		initializeClass(WarlordEmblem.assetPath("/img/character/DeathKnight/Idle.png"),
				"images/characters/defect/shoulder2.png",
				"images/characters/defect/shoulder.png",
				WarlordEmblem.assetPath("/img/character/DeathKnight/corpse.png"),
				getLoadout(), 0.0F, -5.0F, 240.0F, 244.0F,  new EnergyManager(ENERGY_PER_TURN));

	}


	public String getPortraitImageName() {
		return null;
	}

	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(RuneSword.ID);
		if(TalentCount == 1){retVal.add(BloodRealm.ID);}
		if(TalentCount == 3){retVal.add(IceRealm.ID);}
		if(TalentCount == 5){retVal.add(EvilRealm.ID);}
		return retVal;
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add(RuneStrike.ID);
		retVal.add(RuneStrike.ID);
		retVal.add(RuneStrike.ID);
		retVal.add(RuneStrike.ID);
		retVal.add(RuneHeavyBlow.ID);
		retVal.add(DKDefend.ID);
		retVal.add(DKDefend.ID);
		retVal.add(DKDefend.ID);
		retVal.add(DKDefend.ID);
		retVal.add(RuneDefend.ID);


		return retVal;
	}

	public CharSelectInfo getLoadout() {
		return new CharSelectInfo(
				getLocalizedCharacterName(),
				charStrings.TEXT[0],
				START_HP,
				START_HP,
				3,
				START_GOLD,
				5,
				this,
				getStartingRelics(),
				getStartingDeck(),
				false);
	}


	@Override
	public String getTitle(PlayerClass playerClass) {
		return charStrings.NAMES[1];
	}

	@Override
	public AbstractCard.CardColor getCardColor() {
		return AbstractCard.CardColor.BLUE;
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return new RuneHeavyBlow();
	}

	@Override
	public Color getCardTrailColor() {
		return Color.SKY.cpy();
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return 4;
	}

	@Override
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontBlue;
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.mainMenuScreen.charSelectScreen.bgCharImg = CharacterSelectScreenPatches.updateBgImg();

		CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", MathUtils.random(-0.2F, 0.2F));
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_MAGIC_BEAM_SHORT";
	}

	@Override
	public String getLocalizedCharacterName() {
		return charStrings.NAMES[0];
	}

	@Override
	public AbstractPlayer newInstance() {
		return new DeathKnight(this.name, AbstractPlayerEnum.DeathKnight);
	}

	@Override
	public String getSpireHeartText() {
		return CardCrawlGame.languagePack.getEventString("WarlordEmblem:SpireHeart_DeathKnight").DESCRIPTIONS[0];
	}



	@Override
	public Color getSlashAttackColor() { return Color.SKY;}



	@Override
	public String getVampireText() {
		return CardCrawlGame.languagePack.getEventString("WarlordEmblem:Vampires_DeathKnight").DESCRIPTIONS[0];
	}

	@Override
	public Color getCardRenderColor() {
		return Color.SKY;
	}


	@Override
	public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
		return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL
		};
	}

	public void damage(DamageInfo info) {
		super.damage(info);
	}
	}

