package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CrystalCenterPower extends AbstractPower {
    public static final String POWER_ID = WarlordEmblem.makeID("CrystalCenterPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("CrystalCenterPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public CrystalCenterPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = ImageMaster.loadImage("images/powers/32/drawCardRed.png");
    }

    public void updateDescription() { this.description = powerStrings.DESCRIPTIONS[0]; }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageType.NORMAL) {
            return 12f;
        } else {
            return damage;
        }
    }
}
