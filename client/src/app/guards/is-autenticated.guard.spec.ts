import { TestBed, async, inject } from '@angular/core/testing';

import { IsAutenticatedGuard } from './is-autenticated.guard';

describe('IsAutenticatedGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IsAutenticatedGuard]
    });
  });

  it('should ...', inject([IsAutenticatedGuard], (guard: IsAutenticatedGuard) => {
    expect(guard).toBeTruthy();
  }));
});
