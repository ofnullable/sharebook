import React from 'react';

import { StarWrapper, FilledStar, EmptyStar } from './StarRating.styled';

const StarRating = ({ readOnly, score, clickHandler }) => {
  return readOnly ? (
    <StarWrapper>
      {Array.from(new Array(5)).map((_, i) =>
        i >= score ? (
          <EmptyStar key={i} className='material-icons'>
            star_border
          </EmptyStar>
        ) : (
          <FilledStar key={i} className='material-icons'>
            star
          </FilledStar>
        )
      )}
    </StarWrapper>
  ) : (
    <StarWrapper>
      {Array.from(new Array(5)).map((_, i) =>
        i >= score ? (
          <EmptyStar key={i} id={i + 1} className='material-icons' onClick={clickHandler}>
            star_border
          </EmptyStar>
        ) : (
          <FilledStar key={i} id={i + 1} className='material-icons' onClick={clickHandler}>
            star
          </FilledStar>
        )
      )}
    </StarWrapper>
  );
};

export default StarRating;
