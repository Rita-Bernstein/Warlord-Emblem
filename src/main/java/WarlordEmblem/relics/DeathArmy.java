package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class DeathArmy extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("DeathArmy");
    private static final int MAX = 10;

    public DeathArmy() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/death_army.png")), RelicTier.RARE, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() { this.counter = 0; }

    public void atTurnStart() {
        if (!this.grayscale) {
            this.counter++;
        }
        if (this.counter == 4) {
            flash();
            addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(8, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if(AbstractDungeon.player.hasRelic("Necronomicon")){
                    AbstractDungeon.actionManager
                            .addToBottom(new ApplyPowerAction(mo, null, new PoisonPower(mo,  null,9), 9));
                }else{
                    AbstractDungeon.actionManager
                            .addToBottom(new ApplyPowerAction(mo, null, new PoisonPower(mo,  null,8), 8));
                }

            }
            this.counter = -1;
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }


    public AbstractRelic makeCopy() {
        return new DeathArmy();
    }
}
