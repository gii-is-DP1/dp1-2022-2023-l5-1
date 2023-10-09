package org.springframework.samples.minesweeper.platform;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Minesweeper controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class PlatformService {

	private PlatformRepository platformRepository;

	@Autowired
	public PlatformService(PlatformRepository platformRepository) {
		this.platformRepository = platformRepository;
	}

	@Transactional
	public void savePlatform(Platform platform) throws DataAccessException {
		platformRepository.save(platform);
	}
	
	@Transactional(readOnly = true)
	public Platform findPlatform(String platform) {
		return platformRepository.findPlatformById(Integer.valueOf(platform));
	}

	@Transactional(readOnly = true)	
	public Collection<Platform> findPlatforms() throws DataAccessException {
		return (Collection<Platform>) platformRepository.findAll();
	}

	@Transactional(readOnly = true)
    public List<Platform> getAllPlatformOrdered(Integer page, Pageable p) {
        return platformRepository.getAllPlatformOrdered(p);
    }
    	
}