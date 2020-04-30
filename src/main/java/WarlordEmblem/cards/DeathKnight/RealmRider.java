package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import WarlordEmblem.powers.RealmIncreasePower;
import WarlordEmblem.powers.RealmRiderPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;

public class RealmRider extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("RealmRider");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/Power.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public RealmRider() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CustomTagsEnum.Rune_Tag);
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new RealmIncreasePower(AbstractDungeon.player,this.magicNumber)));
        super.triggerOnExhaust();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new RainbowCardEffect()));
        addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new RealmRiderPower(p),0));

        for(AbstractCard c : AbstractDungeon.player.hand.group){
            updateCardGlowColor(c);
        }
        for(AbstractCard c : AbstractDungeon.player.drawPile.group){
            updateCardGlowColor(c);
        }
        for(AbstractCard c : AbstractDungeon.player.discardPile.group){
            updateCardGlowColor(c);
        }
    }

    private void updateCardGlowColor(AbstractCard c){
        if(c.tags.contains(CustomTagsEnum.Blood_Realm_Tag)){
            c.glowColor = Color.RED.cpy();
        }
        else if(c.tags.contains(CustomTagsEnum.Evil_Realm_Tag)){
            c.glowColor = Color.GREEN.cpy();
        }
        else if(c.tags.contains(CustomTagsEnum.Ice_Realm_Tag)){
            c.glowColor = Color.BLUE.cpy();
        }
        else {
            c.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public AbstractCard makeCopy() {
        return new RealmRider();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            updateCost(-1);
            upgradeMagicNumber(1);
        }
    }
}
