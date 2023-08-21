package com.zlht.pbr.algorithm.developer.api.remote.service.impl;

import com.zlht.pbr.algorithm.developer.api.base.impl.BaseServiceImpl;
import com.zlht.pbr.algorithm.developer.api.remote.service.ReviewServicesI;
import com.zlht.pbr.algorithm.developer.model.Review;
import com.zlht.pbr.algorithm.developer.utils.PageInfo;
import com.zlht.pbr.algorithm.developer.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewServicesI {


    @Override
    public Map<String, Object> developCommitReview(int userId, Review review) {
        return null;
    }

    @Override
    public Result<PageInfo> developerQueryReviewList(int userId, int currentPage, int pageSize, String keyword) {
        return null;
    }

}
