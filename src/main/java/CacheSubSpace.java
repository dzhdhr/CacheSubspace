import com.apple.foundationdb.subspace.Subspace;
import com.apple.foundationdb.tuple.ByteArrayUtil;
import com.apple.foundationdb.tuple.Tuple;

public class CacheSubSpace extends Subspace {
    public CacheSubSpace(){
        super(CACHE_PREFIX);
    }
    public CacheSubSpace(Tuple tuple){
        super(tuple,CACHE_PREFIX);
    }
    public CacheSubSpace(Tuple tuple,byte[]prefix){
        super(tuple,CACHE_PREFIX);
    }

    private static final byte []CACHE_PREFIX =new byte[] {0x01};
    @Override
    public byte[] getKey() {
        //System.out.println(ByteArrayUtil.printable(pack()));
        return pack();
    }

}
