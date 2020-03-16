package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//窥亡之盔 拥有6点以上符文能量，视为同时拥有鲜血领域，冰霜领域和邪恶领域
public class DKHelm extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("DKHelm");
    private static Texture texture = new Texture(WarlordEmblem.assetPath("img/relics/dk_helm.png")) ;

    public DKHelm() {
        super(ID, texture, RelicTier.BOSS, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new DKHelm();
    }
}
