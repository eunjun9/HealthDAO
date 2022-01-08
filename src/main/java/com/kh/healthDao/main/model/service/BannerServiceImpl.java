package com.kh.healthDao.main.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.healthDao.common.model.vo.Paging;
import com.kh.healthDao.main.model.dao.BannerMapper;
import com.kh.healthDao.main.model.vo.Banner;
import com.kh.healthDao.mypage.model.vo.Qna;


@Service("BannerService")
public class BannerServiceImpl implements BannerService{
	
	private final BannerMapper bannerMapper;

	@Autowired
	public BannerServiceImpl(BannerMapper bannerMapper) {
		this.bannerMapper = bannerMapper;
	}

	@Override
	public int insertBanner(Banner banner, String originFileName, String path, String savedName) {
		Map<String, Object> map = new HashMap<>();
		banner.setFile_path("/images/upload/main/");
		banner.setChange_file(savedName);
		
		map.put("banner", banner);
		map.put("originFileName", originFileName);
		System.out.println(banner);
		
		int result1 = bannerMapper.insertBanner(map);
		int result2 = bannerMapper.insertFile(map);
		int result3 = bannerMapper.insertFileDB();
		
		int result = 0;
				
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			result = 1;
		}
				
		return result;
	}

	@Override
	public int deleteBanner(String[] arr) {

		int result1 = bannerMapper.deleteBanner(arr);
		int result2 = bannerMapper.deleteBanner2(arr);
		int result3 = bannerMapper.deleteBanner3(arr);

		int result = 0;
		
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			result = 1;
		}
		return result;
	}

	@Override
	public List<Banner> bannerRankList() {
		// 메인배너 랭킹 리스트		
		return bannerMapper.bannerRankList();
	}

	@Override
	public Map<String, Object> findBannerList(int page) {
		int listCount = bannerMapper.getBannerListCount();
		Paging pi = new Paging(page, listCount, 5, 10);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> pageRow = new HashMap<>();
		pageRow.put("page", page);
		pageRow.put("startRow", startRow);
		pageRow.put("endRow", endRow);
		
		List<Banner> bannerList = bannerMapper.findBannreList(pageRow);
		
		Map<String, Object> banner = new HashMap<>();
		
		banner.put("listCount", listCount);
		banner.put("bannerList", bannerList);
		banner.put("pi", pi);
		
		return banner;
	}

	@Override
	public Banner bannerSelect(int main_no) {
		return bannerMapper.bannerSelect(main_no);
	}

	@Override
	public int bannerUpdate(Map<String, Object> map) {
		int result1 = bannerMapper.bannerUpdate(map);
		int result2 = bannerMapper.bannerImgUpdate(map);
				
		int result = 0;
		
		if(result1 > 0 && result2 > 0) {
			result = 1;
		}
		return result;
	}
}
