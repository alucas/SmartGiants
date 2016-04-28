package me.jjm_223.smartgiants.entities.v1_8_R1;

import me.jjm_223.smartgiants.api.entities.INaturalSpawns;
import me.jjm_223.smartgiants.entities.v1_8_R1.nms.SmartGiant;
import me.jjm_223.smartgiants.entities.v1_8_R1.nms.SmartGiantHostile;
import net.minecraft.server.v1_8_R1.BiomeBase;
import net.minecraft.server.v1_8_R1.BiomeMeta;
import net.minecraft.server.v1_8_R1.EnumCreatureType;

import java.util.List;

public class NaturalSpawns implements INaturalSpawns {

    private boolean hostile;
    private int frequency;
    private int minGroupAmount;
    private int maxGroupAmount;

    public NaturalSpawns(boolean hostile, boolean daylight, int frequency, int minGroupAmount, int maxGroupAmount) {
        this.hostile = hostile;
        this.frequency = frequency;
        this.minGroupAmount = minGroupAmount;
        this.maxGroupAmount = maxGroupAmount;

        if (daylight) {
            daylight();
        } else {
            night();
        }
    }

    public void daylight() {
        for (BiomeBase biomeBase : BiomeBase.getBiomes()) {
            List mobs = biomeBase.getMobs(EnumCreatureType.CREATURE);
            if (!mobs.isEmpty()) {
                mobs.add(new BiomeMeta(
                        (hostile ? SmartGiantHostile.class : SmartGiant.class), frequency, minGroupAmount, maxGroupAmount));
            }
        }
    }

    public void night() {
        for (BiomeBase biomeBase : BiomeBase.getBiomes()) {
            List mobs = biomeBase.getMobs(EnumCreatureType.MONSTER);
            if (!mobs.isEmpty()) {
                mobs.add(new BiomeMeta((hostile ? SmartGiantHostile.class : SmartGiant.class),
                                frequency, minGroupAmount, maxGroupAmount));
            }
        }
    }
}
