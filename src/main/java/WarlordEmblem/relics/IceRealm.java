package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CharacterSelectScreenPatches;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IceRealm extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("IceRealm");;
    private static Texture texture = new Texture(WarlordEmblem.assetPath("img/relics/ice_realm.png")) ;

    public IceRealm() {
        super(ID, texture, RelicTier.RARE, AbstractRelic.LandingSound.MAGICAL);
    }



    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new IceRealm();
    }

}
