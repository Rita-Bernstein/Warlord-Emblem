package WarlordEmblem.powers;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.actions.RuneIndexAction;
import WarlordEmblem.cards.DeathKnight.AbstractDKCard;
import WarlordEmblem.patches.CustomTagsEnum;
import WarlordEmblem.relics.RuneSword;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RuneIndexPower extends AbstractPower {
    public static final String POWER_ID = WarlordEmblem.makeID("RuneIndexPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("RuneIndexPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RuneIndexPower(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        updateDescription();
        this.img = new Texture(WarlordEmblem.assetPath("img/powers/RuneIndexPower.png"));

        //loadRegion("anger");
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1] + this.amount + powerStrings.DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new RuneIndexAction(amount, CustomTagsEnum.Rune_Tag));

        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            rs.plusRune(amount);
    }

}
