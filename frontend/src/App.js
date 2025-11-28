import 'bootstrap/dist/css/bootstrap.min.css';
import './css/JobCard.css';
import { useEffect, useState } from 'react';

function JobCard({ job }) {
    return (
        <div className="col-md-6">
            <div className="card job-card p-3">
                <div className="d-flex align-items-center">
                    <div>
                        <h6 className="mb-1 text-secondary">{job.company}</h6>
                        <h5 className="mb-0 fw-bold">{job.title}</h5>
                    </div>
                </div>

                {/* 위치 */}
                <div className="mt-2 text-muted small">{job.location}</div>

                <div className="d-flex justify-content-between align-items-center mt-2">
                    {/* 마감 정보(endDate) 표시 */}
                    <span className="badge bg-secondary-subtle text-dark">
            {job.endDate}
          </span>

                    {/* 상세 링크 버튼 */}
                    <a
                        href={job.detailLink}
                        target="_blank"
                        rel="noreferrer"
                        className="btn btn-outline-primary btn-sm"
                    >
                        상세 보기
                    </a>
                </div>
            </div>
        </div>
    );
}

export default function App() {
    const [jobs, setJobs] = useState([]);

    useEffect(() => {
        // 이 부분이 아까 브라우저에서 보던 /api/joblists 호출하는 부분
        fetch('/api/jobList')
            .then((res) => res.json())
            .then((data) => setJobs(data))
            .catch((err) => console.error('API 호출 오류:', err));
    }, []);

    return (
        <div className="container my-4">
            <h3 className="fw-bold mb-4">
                전체 공고 <span className="text-muted fs-6">총 {jobs.length}건</span>
            </h3>

            <div className="row g-3">
                {jobs.map((job) => (
                    <JobCard key={job.id} job={job} />
                ))}
            </div>
        </div>
    );
}