package WarlordEmblem.relics.quest;

import java.util.HashMap;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardRogue;
import WarlordEmblem.cards.quest.QuestCardRogueReward;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestRogue extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestRogue");
    private final static int COUNT = 5;
    private HashMap<String, Integer> map = new HashMap<String, Integer>();
    private String maxName = "";

    public QuestRogue() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_rogue.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atPreBattle() {
        super.atPreBattle();
        map.clear();
        maxName = "";
    }

    public AbstractRelic makeCopy() {
        return new QuestRogue();
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BorderLongFlashEffect(Color.GRAY), 0.0F, true));
        this.startQuest();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if (state != STATE_IN_PROGRESS)
            return;
        String name = card.originalName;
        if (!map.containsKey(name)) {
            map.put(name, 1);
            if (counter == 0) {
                flash();
                counter = 1;
                maxName = name;
                String display = QuestCardRogue.NAME + counter + "/" + COUNT + maxName;
                AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            }
        } else {
            int oldValue = map.get(name);
            int newValue = oldValue + 1;
            map.put(name, newValue);
            if (counter < newValue) {
                flash();
                counter = newValue;
                maxName = name;
                String display = QuestCardRogue.NAME + counter + "/" + COUNT + maxName;
                AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
                if (counter >= COUNT) {
                    state = STATE_FINISHED;
                    AbstractDungeon.actionManager
                            .addToBottom(new MakeTempCardInHandAction(new QuestCardRogueReward(), false));
                }
            }
        }
    }
}
