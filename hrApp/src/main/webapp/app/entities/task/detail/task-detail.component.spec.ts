import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TaskDetailComponent } from './task-detail.component';

describe('Task Management Detail Component', () => {
  let comp: TaskDetailComponent;
  let fixture: ComponentFixture<TaskDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TaskDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ task: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TaskDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TaskDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load task on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.task).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
