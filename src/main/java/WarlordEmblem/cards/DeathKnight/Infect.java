package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import WarlordEmblem.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;

public class Infect extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("Infect");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/infect.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public Infect() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CustomTagsEnum.Evil_Realm_Tag);
        this.tags.add(CustomTagsEnum.Realm_Tag);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = super.getRuneCount();
        if (amount > this.magicNumber)
            amount = this.magicNumber;

        int times = amount;
        if (hasEvilRealm())
            times += 1+AbstractDKCard.RealmMagicNumber;

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.GREEN)));
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

        if (randomMonster != null) {
            addToBot(new VFXAction(new PotionBounceEffect(p.hb.cX, p.hb.cY, randomMonster.hb.cX, this.hb.cY), 0.4F));
        }
        addToBot(new BouncingFlaskAction(randomMonster, 1, times));
        /*
        for (int i = 0; i < times; i++)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, 1), 1));
        */
        super.useRune(amount);
    }

    public AbstractCard makeCopy() {
        return new Infect();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}
