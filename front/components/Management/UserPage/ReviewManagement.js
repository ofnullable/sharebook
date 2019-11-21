import React from 'react';
import Link from 'next/link';
import { useSelector } from 'react-redux';

import ReviewItem from '@components/Review/ReviewItem';

import { BookTitle } from './ReviewManagement.styled';
import { EmptyReview } from '@components/Review/ReviewItem/index.styled';

const ReviewManagement = () => {
  const { data } = useSelector(state => state.review.myReviewList);
  return (
    <div>
      {data.length ? (
        data.map(review => (
          <div key={review.id}>
            <Link href='/book/[id]' as={`/book/${review.bookId}`}>
              <BookTitle>
                <h2>{review.bookTitle}</h2>
                <span>{review.bookAuthor}</span>
              </BookTitle>
            </Link>
            <ReviewItem style={{ padding: '0' }} review={review} />
          </div>
        ))
      ) : (
        <EmptyReview>리뷰가 존재하지 않아요 ㅠ^ㅠ</EmptyReview>
      )}
    </div>
  );
};

export default ReviewManagement;
