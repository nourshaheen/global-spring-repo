import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegion } from '../region.model';
import { RegionService } from '../service/region.service';
import { RegionDeleteDialogComponent } from '../delete/region-delete-dialog.component';

@Component({
  selector: 'jhi-region',
  templateUrl: './region.component.html',
})
export class RegionComponent implements OnInit {
  regions?: IRegion[];
  isLoading = false;

  constructor(protected regionService: RegionService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.regionService.query().subscribe({
      next: (res: HttpResponse<IRegion[]>) => {
        this.isLoading = false;
        this.regions = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IRegion): number {
    return item.id!;
  }

  delete(region: IRegion): void {
    const modalRef = this.modalService.open(RegionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.region = region;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
