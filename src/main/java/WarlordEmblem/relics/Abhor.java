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

public class Abhor extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("Abhor");
    private static final int MAX = 10;

    public Abhor() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/abhor.png")), RelicTier.UNCOMMON, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        this.counter = 0;
        super.onEquip();
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        this.counter++;
        if (this.counter >= MAX) {
            flash();
            if(AbstractDungeon.player.hasRelic("SsserpentHead")){
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(4, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
            addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));

            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(mo, null, new PoisonPower(mo,  null,3), 3));
            }

            this.counter = 0;
        }
    }


    public AbstractRelic makeCopy() {
        return new Abhor();
    }
}
