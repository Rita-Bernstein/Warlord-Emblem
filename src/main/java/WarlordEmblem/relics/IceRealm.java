package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CharacterSelectScreenPatches;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IceRealm extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("Ice Realm");;
    private static Texture texture = new Texture(WarlordEmblem.assetPath("/img/relics/ice_realm.png")) ;

    public IceRealm() {
        super(ID, texture, AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    public void onEquip(){
        if (!(CharacterSelectScreenPatches.TalentCount == 3||
                CharacterSelectScreenPatches.TalentCount == 1+3||
                CharacterSelectScreenPatches.TalentCount == 3+5||
                CharacterSelectScreenPatches.TalentCount == 1+3+5)){
            CharacterSelectScreenPatches.TalentCount += 3;
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new IceRealm();
    }

}
