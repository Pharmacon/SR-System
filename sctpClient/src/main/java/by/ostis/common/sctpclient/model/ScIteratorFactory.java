package by.ostis.common.sctpclient.model;

public class ScIteratorFactory {

    public static ScIterator create3FAA(ScParameter scAddressFirst, ScParameter scElementTypeSecond,
            ScParameter scElementTypeThird) {

        return buildScIterator(scAddressFirst, scElementTypeSecond, scElementTypeThird);
    }

    public static ScIterator create3AAF(ScParameter scElementTypeFirst, ScParameter scElementTypeSecond,
            ScParameter scAddress) {

        return buildScIterator(scElementTypeFirst, scElementTypeSecond, scAddress);
    }

    public static ScIterator create3FAF(ScParameter scAddressFirst, ScParameter scElementTypeSecond,
            ScParameter scAddressThird) {

        return buildScIterator(scAddressFirst, scElementTypeSecond, scAddressThird);
    }

    public static ScIterator create5FAAAF(ScParameter scAddressFirst, ScParameter scElementTypeSecond,
            ScParameter scElementTypeThird, ScParameter scElementTypeForth, ScParameter scAddressFifth) {

        return buildScIterator(scAddressFirst, scElementTypeSecond, scElementTypeThird, scElementTypeForth,
                scAddressFifth);
    }

    public static ScIterator create5AAFAF(ScParameter scElementTypeFirst, ScParameter scElementTypeSecond,
            ScParameter scAddressThird, ScParameter scElementTypeForth, ScParameter scAddressFifth) {

        return buildScIterator(scElementTypeFirst, scElementTypeSecond, scAddressThird, scElementTypeForth,
                scAddressFifth);
    }

    public static ScIterator create5FAFAF(ScParameter scAddressFirst, ScParameter scElementTypeSecond,
            ScParameter scAddressThird, ScParameter scElementTypeForth, ScParameter scAddressFifth) {

        return buildScIterator(scAddressFirst, scElementTypeSecond, scAddressThird, scElementTypeForth,
                scAddressFifth);
    }

    public static ScIterator create5FAFAA(ScParameter scAddressFirst, ScParameter scElementTypeSecond,
            ScParameter scAddressThird, ScParameter scElementTypeForth, ScParameter scElementTypeFifth) {

        return buildScIterator(scAddressFirst, scElementTypeSecond, scAddressThird, scElementTypeForth,
                scElementTypeFifth);
    }

    public static ScIterator create5FAAAA(ScParameter scAddressFirst, ScParameter scElementTypeSecond,
            ScParameter scElementThird, ScParameter scElementTypeForth, ScParameter scElementTypeFifth) {

        return buildScIterator(scAddressFirst, scElementTypeSecond, scElementThird, scElementTypeForth,
                scElementTypeFifth);
    }

    public static ScIterator create5AAFAA(ScParameter scElementTypeFirst, ScParameter scElementTypeSecond,
            ScParameter scAddressThird, ScParameter scElementTypeForth, ScParameter scElementTypeFifth) {

        return buildScIterator(scElementTypeFirst, scElementTypeSecond, scAddressThird, scElementTypeForth,
                scElementTypeFifth);
    }

    private static ScIterator buildScIterator(ScParameter... parameters) {

        ScIterator scIterator = new ScIterator();
        for (ScParameter scParameter : parameters) {
            scIterator.registerParameter(scParameter);
        }
        return scIterator;
    }
}
