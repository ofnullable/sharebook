export const SERVER_URL = 'http://localhost:8081'; // can set dev server, test server, real server url with process.env variable

export const IMAGE_BASE_URL = SERVER_URL;

export const BOOK_STATUS = {
  AVAILABLE: 'AVAILABLE',
  UNAVAILABLE: 'UNAVAILABLE',
};

export const LENDING_STATUS = {
  REQUESTED: 'REQUESTED',
  CANCELED: 'CANCELED',
  ACCEPTED: 'ACCEPTED',
  REJECTED: 'REJECTED',
  RETURNED: 'RETURNED',
};
