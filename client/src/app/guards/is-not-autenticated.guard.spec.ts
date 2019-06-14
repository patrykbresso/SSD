import { TestBed, async, inject } from '@angular/core/testing';

import { IsNotAutenticatedGuard } from './is-not-autenticated.guard';

describe('IsNotAutenticatedGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IsNotAutenticatedGuard]
    });
  });

  it('should ...', inject([IsNotAutenticatedGuard], (guard: IsNotAutenticatedGuard) => {
    expect(guard).toBeTruthy();
  }));
});
