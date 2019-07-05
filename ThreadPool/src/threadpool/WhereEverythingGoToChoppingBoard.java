package threadpool;

public class WhereEverythingGoToChoppingBoard {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        pool.add(new MissionImpossible());
        pool.add(new MissionDangerous());
        pool.execute();
        pool.execute();
    }
}
