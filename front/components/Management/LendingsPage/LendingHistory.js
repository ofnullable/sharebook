import React from 'react';
import Link from 'next/link';
import moment from 'moment';

import { IMAGE_BASE_URL } from '@utils/consts';
import LendingButton from './LendingButton';

import {
  LendingHistoryWrapper,
  ThumbnailImage,
  LendingInfoWrapper,
  ButtonWrapper,
} from './LendingsHistory.styled';

moment.locale('ko');

const LendingHistory = ({ data }) => {
  return (
    <LendingHistoryWrapper>
      <Link href='/book/[id]' as={`/book/${data.book.id}`}>
        <a>
          <ThumbnailImage
            src={`${IMAGE_BASE_URL}${data.book.imageUri}`}
            alt={`이미지 - ${data.book.title}`}
          />
        </a>
      </Link>
      <LendingInfoWrapper>
        <span>{data.book.category}</span>
        <p>{data.book.title}</p>
        <p>{data.endedAt && moment(data.endedAt).from(data.startedAt)}</p>
        <span>{data.startedAt && moment(data.startedAt).format('YYYY.MM.DD hh:mm')}</span>
      </LendingInfoWrapper>
      <ButtonWrapper>
        <LendingButton currentStatus={data.currentStatus} />
      </ButtonWrapper>
    </LendingHistoryWrapper>
  );
};

export default LendingHistory;
