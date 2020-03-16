package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.DeathKnight.AbstractDKCard;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class DK_Ghoul extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("DK_Ghoul");
    private int amount = 1;

    public DK_Ghoul() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/ghoul.png")), RelicTier.COMMON, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + "1" + DESCRIPTIONS[1] + "1" + DESCRIPTIONS[2] + "1" + DESCRIPTIONS[3];
    }

    @Override
    public void onEquip() {
        this.counter = 1;
        this.amount = 1;
        super.onEquip();
    }

    @Override
    public void atTurnStartPostDraw() {
        flash();
        addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.counter, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if(AbstractDungeon.player.hasRelic("GremlinMask")){
                addToBot(new ApplyPowerAction(mo, null, new PoisonPower(mo, null, this.counter+1), this.counter+1));
            }else {
                addToBot(new ApplyPowerAction(mo, null, new PoisonPower(mo, null, this.counter), this.counter));
            }

        }

        super.atTurnStartPostDraw();
    }

    public void getNewGhoul(){
        flash();
        this.counter ++;
        this.amount = this.counter;
        getUpdatedDescription();
    }

    public AbstractRelic makeCopy() {
        //this.counter++;
        return new DK_Ghoul();
    }
}
