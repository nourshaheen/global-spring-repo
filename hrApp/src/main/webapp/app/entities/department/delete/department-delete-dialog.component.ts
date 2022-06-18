import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDepartment } from '../department.model';
import { DepartmentService } from '../service/department.service';

@Component({
  templateUrl: './department-delete-dialog.component.html',
})
export class DepartmentDeleteDialogComponent {
  department?: IDepartment;

  constructor(protected departmentService: DepartmentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.departmentService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
