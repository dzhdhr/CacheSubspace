import com.apple.foundationdb.Database;
import com.apple.foundationdb.FDB;
import com.apple.foundationdb.Transaction;
import com.apple.foundationdb.directory.DirectorySubspace;
import com.apple.foundationdb.subspace.Subspace;
import com.apple.foundationdb.tuple.ByteArrayUtil;
import com.apple.foundationdb.tuple.Tuple;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FDB fdb = FDB.selectAPIVersion(620);
        Database db = null;
        DirectorySubspace rootDirectory = null;
        try {
            db = fdb.open();
        } catch (Exception e) {
            System.out.println("ERROR: the database is not successfully opened: " + e);
        }

        try {

            Subspace s = new CacheSubSpace(new Tuple().add("TEST"));
            System.out.println(ByteArrayUtil.printable(s.getKey()));
            Transaction tx = db.createTransaction();
            tx.set(s.getKey(),"test".getBytes());
            tx.commit().join();
            System.out.println(ByteArrayUtil.printable(db.createTransaction().get(s.getKey()).join()));

        } catch (Exception e) {
            System.out.println("ERROR: the root directory is not successfully opened: " + e);
        }
    }
}
