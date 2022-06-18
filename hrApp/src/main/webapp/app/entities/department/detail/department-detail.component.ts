import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDepartment } from '../department.model';

@Component({
  selector: 'jhi-department-detail',
  templateUrl: './department-detail.component.html',
})
export class DepartmentDetailComponent implements OnInit {
  department: IDepartment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ department }) => {
      this.department = department;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
