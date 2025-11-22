/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.util.AttributeSet
 *  android.util.Xml
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.AnimationSet
 *  android.view.animation.RotateAnimation
 *  android.view.animation.ScaleAnimation
 *  android.view.animation.TranslateAnimation
 *  java.io.IOException
 *  java.lang.Class
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.reflect.Constructor
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package cn.pedant.SweetAlert;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OptAnimationLoader {
    private static Animation createAnimationFromXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return OptAnimationLoader.createAnimationFromXml(context, xmlPullParser, null, Xml.asAttributeSet((XmlPullParser)xmlPullParser));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static Animation createAnimationFromXml(Context var0, XmlPullParser var1_1, AnimationSet var2_2, AttributeSet var3_3) throws XmlPullParserException, IOException {
        var4_4 = var1_1.getDepth();
        var5_5 = null;
        do {
            block19 : {
                block20 : {
                    if ((var6_6 = var1_1.next()) == (var7_7 = 3)) {
                        if (var1_1.getDepth() <= var4_4) return var5_5;
                    }
                    if (var6_6 == 1) return var5_5;
                    if (var6_6 != 2) continue;
                    var8_8 = var1_1.getName();
                    var8_8.hashCode();
                    switch (var8_8.hashCode()) {
                        case 1052832078: {
                            if (!var8_8.equals((Object)"translate")) break;
                            var7_7 = 4;
                            ** break;
                        }
                        case 109250890: {
                            if (!var8_8.equals((Object)"scale")) {
                                break;
                            }
                            break block19;
                        }
                        case 92909918: {
                            if (!var8_8.equals((Object)"alpha")) break;
                            var7_7 = 2;
                            ** break;
                        }
                        case 113762: {
                            if (!var8_8.equals((Object)"set")) break;
                            var7_7 = 1;
                            ** break;
                        }
                        case -925180581: {
                            if (var8_8.equals((Object)"rotate")) break block20;
                        }
                    }
                    var7_7 = -1;
                    ** break;
                }
                var7_7 = 0;
            }
            switch (var7_7) {
                default: {
                    try {
                        var5_5 = (Animation)Class.forName((String)var8_8).getConstructor(new Class[]{Context.class, AttributeSet.class}).newInstance(new Object[]{var0, var3_3});
                        ** break;
                    }
                    catch (Exception var12_9) {
                        throw new RuntimeException("Unknown animation name: " + var1_1.getName() + " error:" + var12_9.getMessage());
                    }
                }
                case 4: {
                    var5_5 = new TranslateAnimation(var0, var3_3);
                    ** break;
                }
                case 3: {
                    var5_5 = new ScaleAnimation(var0, var3_3);
                    ** break;
                }
                case 2: {
                    var5_5 = new AlphaAnimation(var0, var3_3);
                    ** break;
                }
                case 1: {
                    var5_5 = new AnimationSet(var0, var3_3);
                    (AnimationSet)var5_5;
                    OptAnimationLoader.createAnimationFromXml(var0, var1_1, (AnimationSet)var5_5, var3_3);
                    ** break;
                }
                case 0: 
            }
            var5_5 = new RotateAnimation(var0, var3_3);
lbl57: // 6 sources:
            if (var2_2 == null) continue;
            var2_2.addAnimation((Animation)var5_5);
        } while (true);
    }

    /*
     * Exception decompiling
     */
    public static Animation loadAnimation(Context var0, int var1_1) throws Resources.NotFoundException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[CATCHBLOCK]], but top level block is 1[TRYBLOCK]
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:432)
        // org.benf.cfr.reader.b.a.a.j.d(Op04StructuredStatement.java:484)
        // org.benf.cfr.reader.b.a.a.i.a(Op03SimpleStatement.java:607)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:692)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
        // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
        // org.benf.cfr.reader.entities.g.p(Method.java:396)
        // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
        // org.benf.cfr.reader.entities.d.b(ClassFile.java:792)
        // org.benf.cfr.reader.b.a(Driver.java:128)
        // org.benf.cfr.reader.a.a(CfrDriverImpl.java:63)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.decompileWithCFR(JavaExtractionWorker.kt:61)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.doWork(JavaExtractionWorker.kt:130)
        // com.njlabs.showjava.decompilers.BaseDecompiler.withAttempt(BaseDecompiler.kt:108)
        // com.njlabs.showjava.workers.DecompilerWorker$b.run(DecompilerWorker.kt:118)
        // java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
        // java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
        // java.lang.Thread.run(Thread.java:919)
        throw new IllegalStateException("Decompilation failed");
    }
}

