package work.flipped.community.dao.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import work.flipped.community.entity.DiscussPost;

@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {

}
