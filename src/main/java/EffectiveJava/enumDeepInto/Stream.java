package EffectiveJava.enumDeepInto;

import static com.google.common.base.MoreObjects.toStringHelper;
import static java.util.Objects.requireNonNull;

public class Stream
{
    public enum StreamKind
    {
        PRESENT,
        DATA,
        LENGTH,
        DICTIONARY_DATA,
        DICTIONARY_COUNT,
        SECONDARY,
        ROW_INDEX,
        BLOOM_FILTER,
        BLOOM_FILTER_UTF8,
        IN_DICTIONARY,
        ROW_GROUP_DICTIONARY,
        ROW_GROUP_DICTIONARY_LENGTH,
    }

    private final int column;
    private final StreamKind streamKind;
    private final int length;
    private final boolean useVInts;

    public Stream(int column, StreamKind streamKind, int length, boolean useVInts)
    {
        this.column = column;
        this.streamKind = requireNonNull(streamKind, "streamKind is null");
        this.length = length;
        this.useVInts = useVInts;
    }

    public int getColumn()
    {
        return column;
    }

    public StreamKind getStreamKind()
    {
        return streamKind;
    }

    public int getLength()
    {
        return length;
    }

    public boolean isUseVInts()
    {
        return useVInts;
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("column", column)
                .add("streamKind", streamKind)
                .add("length", length)
                .add("useVInts", useVInts)
                .toString();
    }

    public static void main(String[] args) {
        Stream stream = new Stream(2,StreamKind.DATA,4,true);
        System.out.println(stream.toString());
    }
}

