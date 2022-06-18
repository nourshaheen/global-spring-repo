import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHistoryDetailComponent } from './job-history-detail.component';

describe('JobHistory Management Detail Component', () => {
  let comp: JobHistoryDetailComponent;
  let fixture: ComponentFixture<JobHistoryDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobHistoryDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ jobHistory: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(JobHistoryDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(JobHistoryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load jobHistory on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.jobHistory).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
