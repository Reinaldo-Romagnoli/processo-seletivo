export interface Page<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  currentPage: number; // zero-based index of the current page
}