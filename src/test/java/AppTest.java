import cn.com.yanyuchen.mymusic_web.dao.SingerDao;
import cn.com.yanyuchen.mymusic_web.dao.SongDao;
import cn.com.yanyuchen.mymusic_web.dao.UserDao;
import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.enumItem.Area;
import cn.com.yanyuchen.mymusic_web.enumItem.Platform;
import cn.com.yanyuchen.mymusic_web.service.SingerService;
import cn.com.yanyuchen.mymusic_web.service.SongService;
import cn.com.yanyuchen.mymusic_web.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppTest {



    @Test
    public void test() throws JsonProcessingException {

    }
}
