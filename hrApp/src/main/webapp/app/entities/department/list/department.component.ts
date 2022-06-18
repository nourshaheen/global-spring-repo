import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDepartment } from '../department.model';
import { DepartmentService } from '../service/department.service';
import { DepartmentDeleteDialogComponent } from '../delete/department-delete-dialog.component';

@Component({
  selector: 'jhi-department',
  templateUrl: './department.component.html',
})
export class DepartmentComponent implements OnInit {
  departments?: IDepartment[];
  isLoading = false;

  constructor(protected departmentService: DepartmentService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.departmentService.query().subscribe({
      next: (res: HttpResponse<IDepartment[]>) => {
        this.isLoading = false;
        this.departments = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IDepartment): number {
    return item.id!;
  }

  delete(department: IDepartment): void {
    const modalRef = this.modalService.open(DepartmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.department = department;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
